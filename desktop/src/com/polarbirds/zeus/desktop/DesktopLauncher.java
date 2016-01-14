package com.polarbirds.zeus.desktop;

import com.polarbirds.zeus.net.Server;

public class DesktopLauncher {

  public static void main(String[] args) {
    new Thread(new Server(true)).start();
    //LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    //new LwjglApplication(new ZeusGame(), config);
  }
}
