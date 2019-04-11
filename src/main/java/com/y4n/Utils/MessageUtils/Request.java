package com.y4n.Utils.MessageUtils;

public abstract class Request extends Message {
    private int RequestID;
    private int RequestType;

    public Request(byte[] rawContent) {
        super(rawContent);
    }

    public Request(byte[] header, byte[] body) {
        super(header, body);
    }

    public abstract void getRequestType();

    public abstract void getRequestID();
}
