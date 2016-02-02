package com.polarbirds.zeus.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.input.AInputProcessor;
import com.polarbirds.zeus.world.Tile;

/**
 * Created by Harald on 13.01.2016.
 */
public class Player {

  private static Texture texture = new Texture("data/player.png");

  public final AInputProcessor input;
  public Vector2 pos;
  public Vector2 vel = new Vector2();
  public Vector2 force = new Vector2();
  public float invmass = 1;
  public String username;

  public float width = 1f;

  public static final float LINEAR_DAMPENING = 0.9f;
  public float walkForce = 50f;

  public Player(AInputProcessor input, Vector2 pos, String username) {
    this.input = input;
    this.pos = new Vector2(pos);
    this.username = username;
  }

  public void integrate(float delta, Tile[][] tiles) {
    pos.mulAdd(vel, delta);
    force.add(input.moveX() * walkForce, input.moveY() * walkForce);
    force.scl(invmass);
    vel.mulAdd(force, delta);
    vel.scl(LINEAR_DAMPENING);
    force.setZero();
    System.out.println(pos.toString() + ", " + vel.toString());
  }

  public void draw(SpriteBatch sb) {
    sb.draw(texture, pos.x - width / 2f, pos.y, 1, 1);
  }

  public void applyForce(Vector2 force) {
    this.force.add(force);
  }
}
