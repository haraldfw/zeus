package com.polarbirds.zeus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polarbirds.zeus.net.Multiplayer;

public class ZeusGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
    Multiplayer mp = new Multiplayer();
	}

	@Override
	public void render () {
	}
}
