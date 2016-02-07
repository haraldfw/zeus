package com.polarbirds.zeus.world.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.polarbirds.zeus.world.Tile;
import com.smokebox.lib.pcg.dungeon.Cell;
import com.smokebox.lib.pcg.dungeon.RoomSpreadDungeon;
import com.smokebox.lib.pcg.dungeon.RoomsWithTree;
import com.smokebox.lib.utils.geom.Rectangle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Harald on 30.01.2016.
 */
public class WorldGenerator {

  private WorldGenerator() {
  }

  public static World generateWorld(World.WorldType type) {
    switch (type) {
      case HUB:
        return getHub();
      case DUNGEON:
        return getDungeon();
    }
    return getHub();
  }

  private static World getDungeon() {
    RoomsWithTree roomsWithTree = RoomSpreadDungeon.roomSpreadFloor(50, 5, 5, new Random(0));
    int[] bounds = RoomSpreadDungeon.findBounds(roomsWithTree.rooms);
    // make tile-array with found bounds
    Tile[][] tiles = new Tile[bounds[2] - bounds[0]][bounds[3] - bounds[1]];
    // fill array with walls
    for (Tile[] tiles1 : tiles) {
      Arrays.fill(tiles1, new Tile(Tile.TileCollision.WALL, Tile.TileGraphic.WALL_BASIC));
    }
    // loop through rects in
    for (Cell cell : roomsWithTree.rooms) {
      Rectangle rect = cell.rect;
      for (float x = rect.x; x < rect.x2(); x++) {
        for (float y = rect.y; y < rect.y2(); y++) {
          tiles[(int) x][(int) y] =
              new Tile(Tile.TileCollision.FLOOR, Tile.TileGraphic.FLOOR_BASIC);
        }
      }
    }
    for (Rectangle rect : roomsWithTree.corridors) {
      for (float x = rect.x; x < rect.x2(); x++) {
        for (float y = rect.y; y < rect.y2(); y++) {
          tiles[(int) x][(int) y] =
              new Tile(Tile.TileCollision.FLOOR, Tile.TileGraphic.FLOOR_BASIC);
        }
      }
    }

    com.smokebox.lib.utils.Vector2 sr = roomsWithTree.rooms.get(0).rect.getMidPos();
    Vector2 spawn = new Vector2(sr.x, sr.y);
    HashMap<Tile.TileGraphic, Texture> textures = new HashMap<>();
    textures.put(Tile.TileGraphic.FLOOR_BASIC, new Texture("data/hub_floor.png"));
    textures.put(Tile.TileGraphic.WALL_SIDE, new Texture("data/hub_wall_side.png"));
    textures.put(Tile.TileGraphic.WALL_BASIC, new Texture("data/hub_wall_top.png"));
    return new World(spawn, tiles, textures);
  }

  private static World getHub() {
    Tile[][] tiles = new Tile[40][20];
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
}
