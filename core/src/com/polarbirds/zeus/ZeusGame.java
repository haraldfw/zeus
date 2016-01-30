package com.polarbirds.zeus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.polarbirds.zeus.character.Player;
import com.polarbirds.zeus.hudoverlay.chat.ChatWindow;
import com.polarbirds.zeus.input.Focus;
import com.polarbirds.zeus.input.Keyboard;
import com.polarbirds.zeus.net.UDPClient;
import com.polarbirds.zeus.world.World;

public class ZeusGame extends Game {

  public static final int X_TILES = 32;
  public static final int Y_TILES = 18;
  public static final int PIXELS_PER_TILESIDE = 50;
  public static final int X_PIXELS = X_TILES * PIXELS_PER_TILESIDE;
  public static final int Y_PIXELS = Y_TILES * PIXELS_PER_TILESIDE;

  public static final GameState gameState = GameState.RUNNING;
  public Focus focus = Focus.GAME;
  SpriteBatch sb;
  Texture img;
  World world;
  Player player;
  OrthographicCamera camera;
  ChatWindow notif;
  Keyboard kb;

  public void setFocus(Focus newFocus) {
    this.focus = newFocus;
  }

  @Override
  public void create() {
    Config.getInstance().initPrefs();
    sb = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, X_TILES, Y_TILES);
    camera.position.set(X_TILES / 2, Y_TILES / 2, 0);
    world = new World();
    kb = new Keyboard(camera);
    player = new Player(kb, new Vector2(0, 0), "Harald");
    world.addPlayer(player);
    UDPClient mp = new UDPClient("127.0.0.1");
    notif = new ChatWindow(kb, this);
  }

  @Override
  public void render() {
    float delta = 1f / 60f;
    kb.update();
    world.tick(delta);
    Gdx.gl.glClearColor(0.11f, 0.11f, 0.11f, 1);
    Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    camera.translate(player.pos);
    camera.update();
    sb.begin();
    world.draw(sb);
    notif.update();
    notif.render(sb);
    sb.end();
  }
}
