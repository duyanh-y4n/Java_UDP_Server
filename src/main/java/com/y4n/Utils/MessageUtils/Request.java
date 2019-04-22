package com.y4n.Utils.MessageUtils;

import com.y4n.Utils.MessageUtils.Enum.RequestType;

public class Request extends Message {
    private int requestType;

    public Request(byte[] rawContent) {
        super(rawContent);
        this.requestType = RequestType.NONE.ordinal();
    }

    public Request(byte[] header, byte[] body) {
        super(header, body);
        this.requestType = RequestType.NONE.ordinal();
    }

    public int getRequestType(){
        return this.requestType;
    };

}
