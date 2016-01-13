package com.polarbirds.zeus.world;

import com.polarbirds.zeus.character.Player;

import java.util.ArrayList;

/**
 * Created by Harald on 13.01.2016.
 */
public class Physics {

  public ArrayList<Player> players;

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void removePlayer(Player player) {
    if (players.contains(player)) {
      players.remove(player);
    }
  }

  public void tick(float delta) {
    for(Player p : players) {
      p.tick(delta);
    }
  }
}
