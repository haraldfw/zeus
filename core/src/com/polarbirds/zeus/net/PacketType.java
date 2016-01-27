package com.polarbirds.zeus.net;

import com.polarbirds.zeus.net.packets.InvalidPacket;
import com.polarbirds.zeus.net.packets.LoginPacket;
import com.polarbirds.zeus.net.packets.PlayerStatePacket;

import java.util.BitSet;

/**
 * Created by haraldfw on 1/21/16.
 */
public enum PacketType {
  INVALID(0) {
    @Override
    Packet getPacket(BitSet bits) {
      return new InvalidPacket(bits);
    }
  }, LOGIN(1) {
    @Override
    Packet getPacket(BitSet bits) {
      return new LoginPacket(bits);
    }
  }, PLAYER_STATE(2) {
    @Override
    Packet getPacket(BitSet bits) {
      String[] args = new String[0];
      if (args.length != 3) {
        return new InvalidPacket("Invalid number of arguments for PlayerState-packet");
      } else {
        String username = args[0];
        try {
          float newX = Float.parseFloat(args[1]);
          float newY = Float.parseFloat(args[2]);
          return new PlayerStatePacket(username, newX, newY);
        } catch (NumberFormatException e) {
          return new InvalidPacket("Error in types or order of arguments for PlayerState-packet.");
        }
      }
    }
  },
  CHAT_MESSAGE(3) {
    @Override
    Packet getPacket(BitSet bits) {
      return null;
    }
  };

  public int id;

  PacketType(int id) {
    this.id = id;
  }

  abstract Packet getPacket(BitSet bits);

  public static Packet parsePacket(BitSet bits) {
    int id = (int) bits.get(32, 33).toLongArray()[0];
    for (PacketType pid : PacketType.values()) {
      if (pid.id == id) {
        return pid.getPacket(bits);
      }
    }
    return new InvalidPacket("Invalid packet ID: " + id);
  }

  public static PacketType getType(int id) {
    for (PacketType p : PacketType.values()) {
      if (p.ordinal() == id) {
        return p;
      }
    }
    return PacketType.INVALID;
  }
}
