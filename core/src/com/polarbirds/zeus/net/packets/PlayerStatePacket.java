package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

import java.util.BitSet;

/**
 * Created by Harald on 18.1.16.
 */
public class PlayerStatePacket extends Packet {

  public String username;
  public float newX;
  public float newY;

  public PlayerStatePacket(String username, float newX, float newY) {
    super(PacketType.PLAYER_STATE);
    this.username = username;
    this.newX = newX;
    this.newY = newY;
  }

  @Override
  public BitSet parseBits() {
    return new BitSet();
  }
}
