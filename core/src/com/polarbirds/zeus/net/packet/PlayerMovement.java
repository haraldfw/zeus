package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 18.1.16.
 */
public class PlayerMovement extends Packet {

  public String username;
  public float newX;
  public float newY;

  public PlayerMovement(String msg) {
    String[] comps = msg.split(",");
    if (comps.length != 3) {
      valid = false;
    } else {
      username = comps[0];
      newX = Float.parseFloat(comps[1]);
      newY = Float.parseFloat(comps[2]);
    }
  }
}
