package com.polarbirds.zeus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.StringBuilder;
import com.polarbirds.zeus.character.Player;
import com.polarbirds.zeus.hudoverlay.chat.ChatWindow;
import com.polarbirds.zeus.input.Focus;
import com.polarbirds.zeus.input.Keyboard;
import com.polarbirds.zeus.net.udp.UDPClient;
import com.polarbirds.zeus.world.WorldHandler;
import com.polarbirds.zeus.world.generator.World;
import com.smokebox.lib.pcg.dungeon.RoomSpreadDungeon;

public class ZeusGame extends Game {

  public static final int ART_PIXELS_PER_TILESIDE = 16;
  public static final int X_TILES = 32;
  public static final int Y_TILES = 18;
  public static final int SCREEN_PIXELS_PER_TILESIDE = 50;
  public static final int X_PIXELS = X_TILES * SCREEN_PIXELS_PER_TILESIDE;
  public static final int Y_PIXELS = Y_TILES * SCREEN_PIXELS_PER_TILESIDE;

  public static final GameState gameState = GameState.RUNNING;
  public Focus focus = Focus.GAME;
  SpriteBatch worldSB;
  SpriteBatch hudSB;
  Texture img;
  WorldHandler worldHandler;
  Player localPlayer;
  OrthographicCamera camera;
  ChatWindow notif;
  Keyboard kb;
  private float timestep = 1f/60f;

  public void setFocus(Focus newFocus) {
    this.focus = newFocus;
  }

  @Override
  public void create() {
    Config.getInstance().initPrefs();
    hudSB = new SpriteBatch();
    worldSB = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, X_TILES, Y_TILES);
    worldSB.setProjectionMatrix(camera.combined);
    kb = new Keyboard(camera);
    localPlayer = new Player(kb, new Vector2(0, 0), "Harald");
    worldHandler = new WorldHandler(localPlayer);
    worldHandler.start(World.WorldType.DUNGEON);
    UDPClient udpClient = new UDPClient("127.0.0.1");
    notif = new ChatWindow(kb, this);
  }

  @Override
  public void render() {
    kb.update();
    worldHandler.tick(timestep);

    Gdx.gl.glClearColor(0.11f, 0.11f, 0.11f, 1);
    Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

    camera.setToOrtho(false, X_TILES, Y_TILES);
    camera.position.set(localPlayer.pos.x,localPlayer.pos.y, 0);
    camera.update();
    worldSB.setProjectionMatrix(camera.combined);

    worldSB.begin();
    worldHandler.draw(worldSB);
    worldSB.end();

    notif.update();
    hudSB.begin();
    notif.render(hudSB);
    hudSB.end();


    ShapeRenderer sr = new ShapeRenderer();
    sr.setProjectionMatrix(camera.combined);
    sr.begin(ShapeRenderer.ShapeType.Line);
    sr.line(localPlayer.pos.x, localPlayer.pos.y, camera.position.x, camera.position.y);
    sr.end();

  }
}
