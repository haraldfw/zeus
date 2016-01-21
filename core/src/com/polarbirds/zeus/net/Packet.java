package com.polarbirds.zeus.net;

/**
 * Created by Harald on 13.01.2016.
 */
public abstract class Packet {

  public PacketType type;

  public Packet(PacketType type) {
    this.type = type;
  }

  public abstract byte[] parseBytes();
}
