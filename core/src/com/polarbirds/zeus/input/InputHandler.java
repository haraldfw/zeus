package com.polarbirds.zeus.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.hudoverlay.chat.ChatWindow;

/**
 * Created by Harald on 10.02.2016.
 */
public class InputHandler extends InputListener {

  private ZeusGame game;
  private PlayerInputHandler player;
  private ChatWindow chatWindow;

  public InputHandler(ZeusGame game, PlayerInputHandler player, ChatWindow chatWindow) {
    this.game = game;
    this.player = player;
    this.chatWindow = chatWindow;
  }

  @Override
  public boolean keyTyped(InputEvent event, char c) {
    System.out.println(event.getKeyCode());
    switch (game.focus) {
      case CHAT:
        chatWindow.handleInputEvent(event);
        return true;
    }
    return false;
  }

  @Override
  public boolean keyDown(InputEvent event, int keycode) {
    switch (game.focus) {
      case GAME:
        Key k = Key.get(event.getKeyCode());
        if (k != null) {
          player.handleKeyDown(Key.get(event.getKeyCode()));
        }
        return true;
    }
    return false;
  }
}
