package com.polarbirds.zeus.desktop;

import com.polarbirds.zeus.net.Server;
import com.polarbirds.zeus.net.Packet;

public class DesktopLauncher {

  public static void main(String[] args) {
    new Thread(new Server(true)).start();
    /*LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.foregroundFPS = 60;
    config.backgroundFPS = 60;
    config.useGL30 = true;
    new LwjglApplication(new ZeusGame(), config);*/
    System.out.println(Integer.toBinaryString(Packet.PacketType.LOGIN.ordinal()));
  }
}
