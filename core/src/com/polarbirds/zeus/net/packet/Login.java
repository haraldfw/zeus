package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 18.1.16.
 */
public class Login extends Packet {

  public final String username;

  public Login(String msg) {
    this.username = msg.substring(6);
  }
}
