package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

/**
 * Created by Harald on 18.1.16.
 */
public class PlayerState extends Packet {

  public String username;
  public float newX;
  public float newY;

  public PlayerState(String username, float newX, float newY) {
    super(PacketType.PLAYER_STATE);
    this.username = username;
    this.newX = newX;
    this.newY = newY;
  }

  @Override
  public byte[] parseBytes() {
    return new byte[0];
  }
}
