package com.polarbirds.zeus.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by Harald on 14.1.16.
 */
public class UDPServer implements Runnable {

  public static final int appID = 1947;
  private static final int UDPPORT = 6767;
  private DatagramSocket udpSocket;
  private Socket tcpSocket;
  private boolean running = true;
  private ArrayList<InetAddress> connectedIPs;

  public UDPServer(boolean createLocalShell) {
    if (createLocalShell) {
      new Thread(new Shell(this)).start();
    }
    try {
      udpSocket = new DatagramSocket(UDPPORT);
    } catch (SocketException e) {
      e.printStackTrace();
    }
    System.out.println("UDPServer started");
  }

  public void run() {
    while (running) {
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      try {
        udpSocket.receive(packet);
      } catch (IOException e) {
        if (udpSocket.isClosed()) {
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
    System.out.println("UDPServer stopped");
  }

  public void stopServer() {
    running = false;
    udpSocket.close();
  }

  public void sendData(byte[] data, InetAddress ipAddress) {
    DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, UDPPORT);
    try {
      udpSocket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
