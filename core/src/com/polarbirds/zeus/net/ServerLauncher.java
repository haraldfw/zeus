package com.polarbirds.zeus.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Harald on 14.1.16.
 */
public class ServerLauncher {


  private ServerLauncher() {
  }

  public static void runServer() {
    boolean running = true;
    ClientConnection con = new ClientConnection("127.0.0.1");
    Server server = new Server();
    new Thread(server).start();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (running) {
      String line = null;
      try {
        line = in.readLine().trim();
        System.out.println(line);
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
