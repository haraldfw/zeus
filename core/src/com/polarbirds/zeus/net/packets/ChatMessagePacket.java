package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

import java.util.BitSet;

/**
 * Created by haraldfw on 1/21/16.
 */
public class ChatMessagePacket extends Packet {

  public ChatMessagePacket(BitSet btis) {
    super(PacketType.CHAT_MESSAGE);
  }

  @Override
  public BitSet parseBits() {
    return new BitSet();
  }
}
