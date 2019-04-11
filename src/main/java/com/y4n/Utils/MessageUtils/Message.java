package com.y4n.Utils.MessageUtils;

import com.y4n.Utils.DataFormatUtils;
import org.omg.CORBA.DATA_CONVERSION;

public class Message {
    private int separatorPosition; //header-body separator
    private byte[] rawContent;

    public Message() {
    }

    public Message(byte[] rawContent) {
        this.rawContent = rawContent;
    }

    public Message(byte[] header, byte[] body) {
        this.rawContent = DataFormatUtils.concatBytesArr(header, body);
        this.separatorPosition = header.length;
    }

    public byte[] getHeader() {
        return DataFormatUtils.getSubBytesArr(this.rawContent, 0, this.separatorPosition);
    }

    public byte[] getBody() {
        return DataFormatUtils.getSubBytesArr(this.rawContent, this.separatorPosition, this.rawContent.length);
    }

    public byte[] getRawContent() {
        return this.rawContent;
    }

    public void setBodyLength(int length) {
        this.separatorPosition = this.rawContent.length - length;
    }

    public void setHeaderLength(int length) {
        this.separatorPosition = length;
    }

    public void setRawContent(byte[] rawContent) {
        this.rawContent = rawContent;
    }

    public void deleteHeader() {
        this.rawContent = DataFormatUtils.getSubBytesArr(this.rawContent, this.getHeader().length, this.rawContent.length);
    }

    public void deleteBody() {
        this.rawContent = DataFormatUtils.getSubBytesArr(this.rawContent, 0, this.getHeader().length);
    }

    public void changeHeader(byte[] header){
        this.deleteHeader();
        this.rawContent = DataFormatUtils.concatBytesArr(header,this.rawContent);
    }

    public void changeBody(byte[] body){
        this.deleteBody();
        this.rawContent = DataFormatUtils.concatBytesArr(this.rawContent,body);
    }
}
