package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

/**
 * Created by haraldfw on 1/21/16.
 */
public class ChatMessage extends Packet {

  public ChatMessage() {
    super(PacketType.CHAT_MESSAGE);
  }

  @Override
  public byte[] parseBytes() {
    return new byte[0];
  }
}
