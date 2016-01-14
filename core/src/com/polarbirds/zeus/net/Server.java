package com.polarbirds.zeus.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Harald on 14.1.16.
 */
public class Server implements Runnable {

  private static final int PORT = 6767;
  private DatagramSocket socket;
  private boolean running = true;

  public Server() {
    try {
      socket = new DatagramSocket(PORT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Server started");
  }

  public void run() {
    while (running) {
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      System.out.println("CLIENT > ");
      try {
        socket.receive(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("CLIENT > ");
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
