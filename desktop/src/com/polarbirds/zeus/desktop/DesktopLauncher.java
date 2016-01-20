package com.polarbirds.zeus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.net.Server;

public class DesktopLauncher {

  public static void main(String[] args) {
    new Thread(new Server(true)).start();
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.foregroundFPS = 60;
    config.backgroundFPS = 60;
    new LwjglApplication(new ZeusGame(), config);
  }
}
