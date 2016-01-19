package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 13.01.2016.
 */
public class Packet {

  public enum PacketId {
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
          return new Invalid("Invalid number of arguments for PlayerMovement-packet");
        } else {
          String username = args[0];
          try {
            float newX = Float.parseFloat(args[1]);
            float newY = Float.parseFloat(args[2]);
            return new PlayerMovement(username, newX, newY);
          } catch (NumberFormatException e) {
            return new Invalid("Error in types or order of arguments for PlayerMovement-packet.");
          }
        }
      }

    };

    int id;

    PacketId(int id) {
      this.id = id;
    }

    abstract Packet getPacket(String msg);

    public static Packet parsePacket(String msg) {
      int id = Integer.parseInt(msg.substring(4, 6));
      for (PacketId pid : PacketId.values()) {
        if (pid.id == id) {
          return pid.getPacket(msg);
        }
      }
      return new Invalid("Invalid packet ID: " + id);
    }
  }
}
