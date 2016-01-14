package com.polarbirds.zeus.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Harald on 13.01.2016.
 */
public class ClientConnection extends Thread {

  private static final int PORT = 6767;
  DatagramSocket socket;
  InetAddress ipAddress;

  public ClientConnection(String ipAddress) {
    try {
      socket = new DatagramSocket(PORT);
      this.ipAddress = InetAddress.getByName(ipAddress);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    while (true) {
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("SERVER > " + new String(packet.getData()));
    }
  }

  public void sendData(byte[] data) {
    DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, PORT);
    try {
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
