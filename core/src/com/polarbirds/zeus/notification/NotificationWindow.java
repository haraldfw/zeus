package com.polarbirds.zeus.notification;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.input.AInputProcessor;

import java.util.ArrayList;

/**
 * Created by Harald on 23.01.2016.
 */
public class NotificationWindow {

  BitmapFont font;
  AInputProcessor input;
  boolean entering = false;

  static final int TIME_MSG_NEW = 2500;
  static final int MAX_MSG_SIZE = 500;
  static final int MAX_LIST_SIZE = 50;
  static final int CHAT_INACTIVE_SHOW_ITEMS = 5;

  static final float X_SHIFT = 1;
  static final float Y_SHIFT = 1;
  static final float LINE_HEIGHT = 0.5f;

  ArrayList<Notification> notifications;

  public NotificationWindow(AInputProcessor input) {
    this.input = input;
    FreeTypeFontGenerator fg = new FreeTypeFontGenerator(new FileHandle("data/font.ttf"));
    font = fg.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
    notifications = new ArrayList<>();
    addMsg("Notification-window started successfully");
  }

  public boolean update() {
    if (input.chat()) {
      if (entering) {
        entering = false;
        addMsg("Chat closed!");
      }  else {
        entering = true;
        addMsg("Chat opened!");
      }
    }
    return entering;
  }

  public void render(SpriteBatch spriteBatch) {
    for (int i = 0; i < notifications.size(); i++) {
      if (!entering && i > CHAT_INACTIVE_SHOW_ITEMS) break;
      Notification not = notifications.get(i);
      if (! entering && System.currentTimeMillis() > not.time + TIME_MSG_NEW) break;
      font.draw(spriteBatch, not.msg, ZeusGame.PIXELS_PER_TILESIDE * X_SHIFT, ZeusGame.PIXELS_PER_TILESIDE * (i * LINE_HEIGHT + Y_SHIFT));
    }
  }

  public void addMsg(String msg) {
    if (msg.length() > MAX_MSG_SIZE) return;
    notifications.add(0, new Notification(msg));
    while (notifications.size() > MAX_LIST_SIZE) {
      notifications.remove(notifications.size() - 1);
    }
  }
}
