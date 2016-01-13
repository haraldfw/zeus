package com.polarbirds.zeus.character;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Harald on 13.01.2016.
 */
public class Player {

  public Vector2 pos;
  public Vector2 vel;
  public Vector2 acc;
  public Vector2 force;
  public float invmass = 1;

  public String username;

  public Player(Vector2 pos, String username) {
    this.pos = pos;
    this.username = username;
  }

  public void tick(float delta) {
    acc.mulAdd(force, invmass);
    force.setZero();
    vel.mulAdd(acc, delta);
    pos.mulAdd(vel, delta);
  }

  public void push(Vector2 force) {
    this.force.add(force);
  }
}
