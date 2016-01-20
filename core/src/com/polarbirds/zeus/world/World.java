package com.polarbirds.zeus.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polarbirds.zeus.character.Player;

import java.util.ArrayList;

/**
 * Created by Harald on 13.01.2016.
 */
public class World {

  public ArrayList<Player> connectedPlayers;

  public World() {
    connectedPlayers = new ArrayList<>();
  }

  public void addPlayer(Player player) {
    connectedPlayers.add(player);
  }

  public void removeConnectedPlayer(Player player) {
    if (connectedPlayers.contains(player)) {
      connectedPlayers.remove(player);
    }
  }

  public void tick(float delta) {
    for (Player p : connectedPlayers) {
      p.tick(delta);
    }
  }

  public void draw(SpriteBatch sb) {
    for (Player p : connectedPlayers) {
      p.draw(sb);
    }
  }
}
