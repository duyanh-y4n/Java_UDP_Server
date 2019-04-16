package com.y4n.Utils.MessageUtils;

public class Response extends Message {
    private int responseType;
    private Request correspondingRequest;

    public Response(byte[] rawContent) {
        super(rawContent);
    }

    public Response(byte[] header, byte[] body) {
        super(header, body);
    }

    public Response(int ResponseType, Request correspondingRequest){
        super();
        this.responseType = ResponseType;
        this.correspondingRequest = correspondingRequest;
    }

    public Response(Request correspondingRequest){
        super();
        this.responseType = ResponseTypes.CONFIRMATION;
        this.correspondingRequest = correspondingRequest;
    }

    public int getResponseType() {
        return this.responseType;
    }

    public Request getCorrespondingRequest() {
        return this.correspondingRequest;
    }

    public void setResponseType(int responseType){
        this.responseType = responseType;
    };
}
