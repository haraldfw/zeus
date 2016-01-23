package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

import java.util.BitSet;

/**
 * Created by Harald on 18.1.16.
 */
public class Login extends Packet {

  public final String username;

  public Login(String username) {
    super(PacketType.LOGIN);
    this.username = username;
  }

  @Override
  public BitSet parseBits() {
    return new BitSet();
  }
}
