package com.polarbirds.zeus.net.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Harald on 27.1.16.
 */
public class TCPClient implements Runnable {


  private boolean running;

  @Override
  public void run() {
    try {

      Socket socket = new Socket("localhost", TCPServer.TCPPORT);
    } catch (IOException e) {

    }
    while(running) {

    }
  }
}
