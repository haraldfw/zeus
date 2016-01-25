package com.polarbirds.zeus.net.packets;

import com.polarbirds.zeus.net.Packet;
import com.polarbirds.zeus.net.PacketType;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by Harald on 18.1.16.
 */
public class Invalid extends Packet {

  String msg;

  public Invalid(BitSet bits) {
    super(PacketType.INVALID);
    msg = parseString(bits, 36, -1);
  }

  public Invalid(String msg) {
    super(PacketType.INVALID);
    this.msg = msg;
  }

  @Override
  public BitSet parseBits() {
    return new BitSet();
  }

  protected String parseString(BitSet bits, int from, int to) {
    byte[] ar = bits.toByteArray();
    ByteBuffer bb = ByteBuffer.wrap(Arrays.copyOfRange(ar, from, to < 0 ? ar.length : to));
    Charset cs = Charset.forName("UTF-8");
    CharsetDecoder csd =
        cs.newDecoder().onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT);
    try {
      return csd.decode(bb).toString();
    } catch (CharacterCodingException cce) {
      return "Invalid UTF-8 unicode string! " + cce.getMessage();
    }
  }
}
