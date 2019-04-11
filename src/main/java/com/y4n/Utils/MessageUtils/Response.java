package com.y4n.Utils.MessageUtils;

public abstract class Response extends Message {
    private int ResponseType;

    public Response(byte[] rawContent) {
        super(rawContent);
    }

    public Response(byte[] header, byte[] body) {
        super(header, body);
    }

    public abstract void setResponseType();

    public abstract void setHeader();

    public abstract void setBody();
}
