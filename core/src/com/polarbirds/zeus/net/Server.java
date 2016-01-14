package com.polarbirds.zeus.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Harald on 14.1.16.
 */
public class Server implements Runnable {

  private static final int PORT = 6767;
  private DatagramSocket socket;
  private boolean running = true;

  public Server(boolean createLocalShell) {
    if (createLocalShell) {
      new Thread(new Shell(this)).start();
    }
    try {
      socket = new DatagramSocket(PORT);
    } catch (SocketException e) {
      e.printStackTrace();
    }
    System.out.println("Server started");
  }

  public void run() {
    while (running) {
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        if(socket.isClosed()) {
          System.out.println("Socket closed");
          break;
        } else {
          e.printStackTrace();
        }
      }
      String msg = new String(packet.getData());
      if (msg.trim().equalsIgnoreCase("ping")) {
        sendData("pong".getBytes(), packet.getAddress());
      }
      System.out.println("CLIENT > " + msg);
    }
    System.out.println("Server stopped");
  }

  public void stopServer() {
    running = false;
    socket.close();
  }

  public void sendData(byte[] data, InetAddress ipAddress) {
    DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, PORT);
    try {
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
