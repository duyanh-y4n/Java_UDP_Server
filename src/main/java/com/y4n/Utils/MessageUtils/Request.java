package com.y4n.Utils.MessageUtils;

public abstract class Request<T, E> extends Message {
    private int requestID;
    private int requestType;
    private T headerDAO;
    private E bodyDAO;

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

    public T getHeaderDAO() {
        return this.headerDAO;
    }

    public abstract T parseHeaderDTO();

    public abstract E parseBodyDTO();

}
