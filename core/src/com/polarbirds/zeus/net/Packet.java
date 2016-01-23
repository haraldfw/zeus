package com.polarbirds.zeus.net;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by Harald on 13.01.2016.
 */
public abstract class Packet {

  public static final int APP_ID = 1528389362;

  public PacketType type;

  public Packet(PacketType type) {
    this.type = type;
  }

  public abstract BitSet parseBits();

  public static int getAppId(BitSet bits) {
    return ByteBuffer.allocate(4).put(Arrays.copyOfRange(bits.toByteArray(), 0, 32)).getInt();
  }
}
