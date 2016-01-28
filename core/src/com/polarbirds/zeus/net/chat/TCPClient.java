package com.polarbirds.zeus.net.chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Harald on 27.1.16.
 */
public class TCPClient implements Runnable {

  private boolean running = true;
  private Socket socket;

  @Override
  public void run() {
    try {
      socket = new Socket("localhost", TCPServer.TCPPORT);

    } catch (IOException e) {

    }
    while (running) {

    }
  }

  public synchronized void sendData(String data) throws IOException {
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    out.writeBytes(data);
  }
}
