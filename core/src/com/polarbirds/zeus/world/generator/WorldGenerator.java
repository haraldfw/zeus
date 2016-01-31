package com.polarbirds.zeus.world.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.polarbirds.zeus.world.Tile;

import java.util.HashMap;

/**
 * Created by Harald on 30.01.2016.
 */
public class WorldGenerator {
  public static World generateWorld(World.WorldType type) {
    switch (type) {
      case HUB:
        return getHub();
    }
    return getHub();
  }

  private static World getHub() {
    Tile[][] tiles = new Tile[20][40];
    Vector2 spawn = new Vector2(5, 10);
    for (int x = 0; x < tiles.length; x++) {
      for (int y = 0; y < tiles[0].length; y++) {
        Tile.TileCollision collision;
        Tile.TileGraphic graphic;
        if (x == 0 || x == tiles.length - 1 || y == 0 || y == tiles[0].length - 1) {
          collision = Tile.TileCollision.WALL;
          graphic = Tile.TileGraphic.WALL_BASIC;
        } else {
          collision = Tile.TileCollision.FLOOR;
          graphic = Tile.TileGraphic.FLOOR_BASIC;
        }
        tiles[x][y] = new Tile(collision, graphic);
      }
    }
    HashMap<Tile.TileGraphic, Texture> textures = new HashMap<>();
    textures.put(Tile.TileGraphic.FLOOR_BASIC, new Texture("data/hub_floor.png"));
    textures.put(Tile.TileGraphic.WALL_SIDE, new Texture("data/hub_wall_side.png"));
    textures.put(Tile.TileGraphic.WALL_BASIC, new Texture("data/hub_wall_top.png"));
    return new World(spawn, tiles, textures);
  }

  private WorldGenerator() {
  }
}
