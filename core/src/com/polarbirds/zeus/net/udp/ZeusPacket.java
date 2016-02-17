package com.polarbirds.zeus.net.udp;

/**
 * Created by Harald on 17.02.2016.
 */
public class ZeusPacket {

  public final PacketType type;

  public ZeusPacket(byte id) {
    this.type = PacketType.get(id);
  }

  public enum PacketType {
    LOGIN((byte) 0);

    private byte id;

    PacketType(byte id) {
      this.id = id;
    }

    public static PacketType get(byte b) {
      for (PacketType p : PacketType.values()) {
        if(b == p.id) {
          return p;
        }
      }
      System.out.println("No packet-type exists for id-byte: " + b);
      return null;
    }

    public static void parse(byte[] data) {

    }
  }
}
