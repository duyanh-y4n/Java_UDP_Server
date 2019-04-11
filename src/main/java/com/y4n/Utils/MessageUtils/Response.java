package com.y4n.Utils.MessageUtils;

public abstract class Response<T,E> extends Message {
    private int responseType;
    private T headerDTO;
    private E bodyDTO;
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

    public T getHeaderDTO() {
        return this.headerDTO;
    }

    public Request getCorrespondingRequest() {
        return this.correspondingRequest;
    }

    public void setHeaderDTO(T headerDTO) {
        this.headerDTO = headerDTO;
    }


    public void setResponseType(int responseType){
        this.responseType = responseType;
    };

    public abstract void compileBodyDTOtoBytes(); //change headerDTO to rawBytes

    public abstract void compileHeaderDTOtoBytes(); //change headerDTO to rawBytes
}
