package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

/**
 * Created by Harald on 18.1.16.
 */
public class Invalid extends Packet {

  String msg;

  public Invalid(String msg) {
    super(PacketType.INVALID);
    this.msg = msg;
  }

  @Override
  public byte[] parseBytes() {
    return new byte[0];
  }
}
