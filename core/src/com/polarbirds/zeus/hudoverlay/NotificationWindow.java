package com.polarbirds.zeus.hudoverlay;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.input.AInputProcessor;
import com.polarbirds.zeus.input.Focus;
import com.polarbirds.zeus.input.Keyboard;

import java.util.ArrayList;

/**
 * Created by Harald on 23.01.2016.
 */
public class NotificationWindow {

  static final int TIME_MSG_NEW = 2500;
  static final int MAX_MSG_SIZE = 500;
  static final int MAX_LIST_SIZE = 10;
  static final int CHAT_INACTIVE_SHOW_ITEMS = 5;
  static final float X_SHIFT = 1;
  static final float Y_SHIFT = 1;
  static final float LINE_HEIGHT = 0.5f;
  BitmapFont font;
  AInputProcessor input;
  ArrayList<Notification> notifications;

  TextField textField;

  ZeusGame game;

  public NotificationWindow(Keyboard input, ZeusGame game) {
    this.input = input;
    this.game = game;
    FreeTypeFontGenerator fg = new FreeTypeFontGenerator(new FileHandle("data/font.ttf"));
    font = fg.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    notifications = new ArrayList<>();
    textField = new TextField(font, input, game);
    addMsg("Notification-window started successfully");
  }

  public void update() {
    if (input.chat()) {
      if (game.focus == Focus.CHAT) {
        String text = textField.getText().trim();
        if (!text.isEmpty()) {
          addMsg("you: " + text);
          textField.clearText();
        }
        game.setFocus(Focus.GAME);
      } else {
        game.setFocus(Focus.CHAT);
      }
    }
  }

  public void render(SpriteBatch spriteBatch) {
    boolean chatFocus = game.focus == Focus.CHAT;
    for (int i = 0; i < notifications.size(); i++) {
      if (!chatFocus && i >= CHAT_INACTIVE_SHOW_ITEMS) {
        break;
      }
      Notification not = notifications.get(i);
      if (!chatFocus && System.currentTimeMillis() > not.time + TIME_MSG_NEW) {
        break;
      }
      font.draw(spriteBatch, not.msg, ZeusGame.PIXELS_PER_TILESIDE * X_SHIFT,
                ZeusGame.PIXELS_PER_TILESIDE * (i * LINE_HEIGHT + Y_SHIFT));
    }
    if (chatFocus) {
      textField.draw(spriteBatch);
    }
  }

  public void addMsg(String msg) {
    if (msg.length() > MAX_MSG_SIZE) {
      msg = msg.substring(0, MAX_MSG_SIZE);
    }
    notifications.add(0, new Notification(msg));
    while (notifications.size() > MAX_LIST_SIZE) {
      notifications.remove(notifications.size() - 1);
    }
  }
}
