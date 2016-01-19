package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 18.1.16.
 */
public class PlayerMovement extends Packet {

  public String username;
  public float newX;
  public float newY;

  public PlayerMovement(String username, float newX, float newY) {
    this.username = username;
    this.newX = newX;
    this.newY = newY;
  }
}
