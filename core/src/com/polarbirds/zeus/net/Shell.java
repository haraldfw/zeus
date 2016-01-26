package com.polarbirds.zeus.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Harald on 14.1.16.
 */
public class Shell implements Runnable {

  private UDPServer server;

  public Shell(UDPServer server) {
    this.server = server;
  }

  @Override
  public void run() {
    boolean running = true;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (running) {
      String line = null;
      try {
        line = in.readLine().trim();
        if (line.equalsIgnoreCase("stop") || line.equalsIgnoreCase("quit")) {
          server.stopServer();
          running = false;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
