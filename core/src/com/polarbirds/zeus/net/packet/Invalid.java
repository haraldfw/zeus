package com.polarbirds.zeus.net.packet;

/**
 * Created by Harald on 18.1.16.
 */
public class Invalid extends Packet {

  String msg;

  public Invalid(String msg) {
    this.msg = msg;
  }
}
