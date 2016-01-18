package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 13.01.2016.
 */
public class Packet {

  boolean valid = true;

  public enum PacketId {
    INVALID(0) {
      @Override
      Packet getPacket(String msg) {
        return new Invalid();
      }
    }, LOGIN(1) {
      @Override
      Packet getPacket(String msg) {
        return new Login(msg);
      }
    }, PLAYER_MOVEMENT(2) {
      @Override
      Packet getPacket(String msg) {
        return new PlayerMovement(msg);
      }
    };

    int id;

    PacketId(int id) {
      this.id = id;
    }

    abstract Packet getPacket(String msg);

    static Packet getPacket(int id, String msg) {
      for (PacketId pid : PacketId.values()) {
        if (pid.id == id) {
          pid.getPacket(msg);
        }
      }
      return new Invalid();
    }
  }
}
