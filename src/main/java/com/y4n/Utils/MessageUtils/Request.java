package com.y4n.Utils.MessageUtils;

public class Request extends Message {
    private int requestID;
    private int requestType;

    public Request(byte[] rawContent) {
        super(rawContent);
    }

    public Request(byte[] header, byte[] body) {
        super(header, body);
    }

    public int getRequestType(){
        return this.requestType;
    };

    public int getRequestID(){
        return this.requestID;
    };

}
