package com.polarbirds.zeus.input;

import com.polarbirds.zeus.character.Player;

/**
 * Created by Harald on 10.02.2016.
 */
public class PlayerInputHandler {

  private Player player;

  public PlayerInputHandler(Player player) {
    this.player = player;
  }

  public void handleKeyDown(Key key) {
    switch (key) {
      case PLAYER_MOVE_UP:
        player.applyWalkForce(0, 1);
        break;
      case PLAYER_MOVE_LEFT:
        player.applyWalkForce(-1, 0);
        break;
      case PLAYER_MOVE_DOWN:
        player.applyWalkForce(0, -1);
        break;
      case PLAYER_MOVE_RIGHT:
        player.applyWalkForce(1, 0);
        break;
    }
  }
}
