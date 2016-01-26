package com.polarbirds.zeus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.net.UDPServer;

public class DesktopLauncher {

  public static void main(String[] args) {
    new Thread(new UDPServer(true)).start();
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.foregroundFPS = 60;
    config.backgroundFPS = 60;
    config.useGL30 = true;
    new LwjglApplication(new ZeusGame(), config);
  }
}
