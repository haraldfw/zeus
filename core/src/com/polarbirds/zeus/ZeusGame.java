package com.polarbirds.zeus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polarbirds.zeus.net.ClientConnection;
import com.polarbirds.zeus.net.Server;

public class ZeusGame extends ApplicationAdapter {

  SpriteBatch batch;
  Texture img;

  @Override
  public void create() {
    ClientConnection mp = new ClientConnection("127.0.0.1");
  }

  @Override
  public void render() {

  }
}
