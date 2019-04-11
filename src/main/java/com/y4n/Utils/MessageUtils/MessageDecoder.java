package com.y4n.Utils.MessageUtils;

/*
 * decode raw byte and return content as object
 * */
public interface MessageDecoder<T> {
    public <T> T decodeHeader(byte[] header);

    public <T> T decodeBody(byte[] body);
}
