package com.polarbirds.zeus.net.packet;

import java.nio.ByteBuffer;

/**
 * Created by Harald on 13.01.2016.
 */
public abstract class Packet {

  public enum PacketType {
    INVALID(0) {
      @Override
      Packet getPacket(String msg) {
        return new Invalid(msg);
      }
    }, LOGIN(1) {
      @Override
      Packet getPacket(String msg) {
        return new Login(msg.substring(6));
      }
    }, PLAYER_MOVEMENT(2) {
      @Override
      Packet getPacket(String msg) {
        String[] args = msg.substring(6).split(",");
        if (args.length != 3) {
          return new Invalid("Invalid number of arguments for PlayerState-packet");
        } else {
          String username = args[0];
          try {
            float newX = Float.parseFloat(args[1]);
            float newY = Float.parseFloat(args[2]);
            return new PlayerState(username, newX, newY);
          } catch (NumberFormatException e) {
            return new Invalid("Error in types or order of arguments for PlayerState-packet.");
          }
        }
      }
    };

    public int id;

    PacketType(int id) {
      this.id = id;
    }

    abstract Packet getPacket(String msg);

    public static Packet parsePacket(String msg) {
      int id = Integer.parseInt(msg.substring(4, 6));
      for (PacketType pid : PacketType.values()) {
        if (pid.id == id) {
          return pid.getPacket(msg);
        }
      }
      return new Invalid("Invalid packet ID: " + id);
    }
  }

  public PacketType type;

  public byte[] getdata() {
    return ByteBuffer.allocate(4).putInt(type.ordinal()).array();
  }
}
