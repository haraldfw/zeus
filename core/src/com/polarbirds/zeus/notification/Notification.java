package com.polarbirds.zeus.notification;

/**
 * Created by Harald on 23.01.2016.
 */
public class Notification {
  public final long time;
  public final String msg;

  public Notification(String msg) {
    time = System.currentTimeMillis();
    this.msg = msg;
  }
}
