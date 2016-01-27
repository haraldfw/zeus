package com.polarbirds.zeus.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Harald on 26.1.16.
 */
public class TCPServer implements Runnable {

  public static final int TCPPORT = 6768;
  static boolean running = true;

  private  TCPServer(Socket socket) {
  }

  @Override
  public void run() {
    while (running) {

    }
  }

  public static void start() throws IOException {
    ServerSocket server = new ServerSocket(TCPPORT);
    while (running) {
      Socket s = server.accept();
      new Thread(new TCPServer(s)).start();
    }
  }
}
