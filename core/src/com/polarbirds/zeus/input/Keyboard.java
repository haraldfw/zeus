package com.polarbirds.zeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.polarbirds.zeus.ZeusGame;

import java.util.HashMap;

/**
 * Created by Harald on 14.5.15.
 */
public final class Keyboard extends AInputProcessor {

  OrthographicCamera camera;

  public Keyboard(OrthographicCamera camera) {
    super(getKeys());
    this.camera = camera;
  }

  private static HashMap<String, Integer> getKeys() {
    HashMap<String, Integer> keys = new HashMap<>();
    keys.put("attack1", Input.Keys.J);
    keys.put("attack2", Input.Keys.K);
    keys.put("jump", Input.Keys.SPACE);
    keys.put("interact", Input.Keys.E);
    keys.put("menu", Input.Keys.TAB);
    keys.put("pause", Input.Keys.ESCAPE);
    keys.put("quick1", Input.Keys.NUM_1);
    keys.put("quick2", Input.Keys.NUM_2);
    keys.put("quick3", Input.Keys.NUM_3);
    keys.put("quick4", Input.Keys.NUM_4);
    keys.put("chat", Input.Keys.ENTER);
    return keys;
  }

  @Override
  public float moveX() {
    return (Gdx.input.isKeyPressed(Input.Keys.A) ? -1 : 0) +
        (Gdx.input.isKeyPressed(Input.Keys.D) ? 1 : 0);
  }

  @Override
  public float moveY() {
    return (Gdx.input.isKeyPressed(Input.Keys.W) ? 1 : 0) +
        (Gdx.input.isKeyPressed(Input.Keys.S) ? -1 : 0);
  }

  @Override
  public float lookX() {
    return 1 - 1 / ((Gdx.input.getX() - ZeusGame.X_PIXELS / 2) / (float) ZeusGame.X_PIXELS
        + 1);
  }

  @Override
  public float lookY() {
    return 1 - 1 / ((Gdx.input.getY() - ZeusGame.Y_PIXELS / 2) / (float) ZeusGame.Y_PIXELS
        + 1);
  }

  @Override
  protected boolean getIsDown(int key) {
    return Gdx.input.isKeyPressed(key);
  }

  @Override
  public boolean getQuickSelect1() {
    return Gdx.input.isKeyPressed(Input.Keys.NUM_1);
  }

  @Override
  public boolean getQuickSelect2() {
    return Gdx.input.isKeyPressed(Input.Keys.NUM_2);
  }

  @Override
  public boolean getQuickSelect3() {
    return Gdx.input.isKeyPressed(Input.Keys.NUM_3);
  }

  @Override
  public boolean getQuickSelect4() {
    return Gdx.input.isKeyPressed(Input.Keys.NUM_4);
  }
}
