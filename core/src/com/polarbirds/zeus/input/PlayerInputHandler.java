package com.polarbirds.zeus.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.polarbirds.zeus.character.Player;

/**
 * Created by Harald on 10.02.2016.
 */
public class PlayerInputHandler {

  private Player player;

  public PlayerInputHandler(Player player) {
    this.player = player;
  }

  public void update() {
    if(Gdx.input.isKeyPressed(Input.Keys.W)) {
      player.applyWalkForce(0, 1);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.A)) {
      player.applyWalkForce(-1, 0);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.S)) {
      player.applyWalkForce(0, -1);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.D)) {
      player.applyWalkForce(1, 0);
    }
  }
}
