package com.polarbirds.zeus.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.polarbirds.zeus.input.AInputProcessor;

/**
 * Created by Harald on 13.01.2016.
 */
public class Player {

  private static Sprite sprite = new Sprite(new Texture("data/player.png"));
  public final AInputProcessor input;
  public Vector2 pos;
  public Vector2 vel = new Vector2();
  public Vector2 acc = new Vector2();
  public Vector2 force = new Vector2();
  public float invmass = 1;
  public String username;

  public Player(AInputProcessor input, Vector2 pos, String username) {
    this.input = input;
    this.pos = pos;
    this.username = username;
  }

  public void integrate(float delta) {
    acc.mulAdd(force, invmass);
    force.setZero();
    vel.mulAdd(acc, delta);
    pos.mulAdd(vel, delta);
  }

  public void draw(SpriteBatch sb) {
    sprite.setPosition(pos.x, pos.y);
    sprite.draw(sb);
  }

  public void applyForce(Vector2 force) {
    this.force.add(force);
  }
}
