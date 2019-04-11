package com.y4n.Utils.MessageUtils;

/*
* encode to raw byte content object
* */
public interface MessageEncoder<T> {
    public byte[] encodeHeader(T headerDAO);

    public byte[] encodeBody(T bodyDAO);
}
