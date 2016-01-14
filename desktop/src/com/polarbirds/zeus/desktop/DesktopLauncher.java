package com.polarbirds.zeus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.net.ServerLauncher;

public class DesktopLauncher {

  public static void main(String[] args) {
    String arg = args.length == 0 ? "" : args[0];
    if (arg.equalsIgnoreCase("-s")) {
      ServerLauncher.runServer();
    } else {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
      new LwjglApplication(new ZeusGame(), config);
    }
  }
}
