package com.polarbirds.zeus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polarbirds.zeus.net.ClientConnection;
import com.polarbirds.zeus.net.Server;
import com.polarbirds.zeus.world.Physics;

public class ZeusGame extends Game {

  public static final int X_TILES = 32;
  public static final int Y_TILES = 18;
  public static final int PIXELS_PER_TILESIDE = 50;
  public static final int X_PIXELS = X_TILES * PIXELS_PER_TILESIDE;
  public static final int Y_PIXELS = Y_TILES * PIXELS_PER_TILESIDE;

  SpriteBatch batch;
  Texture img;
  Physics world = new Physics();

  @Override
  public void create() {
    ClientConnection mp = new ClientConnection("127.0.0.1");
  }

  @Override
  public void render() {
    float delta = 1f/60f;
    world.tick(delta);
  }
}
