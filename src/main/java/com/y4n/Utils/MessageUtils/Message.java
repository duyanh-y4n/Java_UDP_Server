package com.y4n.Utils.MessageUtils;

import com.y4n.Utils.DataFormatUtils;

public class Message {
    private int separatorPosition;
    private byte[] rawContent;

    public Message(byte[] rawContent) {
        this.rawContent = rawContent;
    }

    public Message(byte[] header, byte[] body) {
        this.rawContent = DataFormatUtils.concatBytesArr(header,body);
        this.separatorPosition = header.length;
    }

    public byte[] getHeader() {
        return DataFormatUtils.getSubBytesArr(this.rawContent,0,this.separatorPosition);
    }

    public byte[] getBody() {
        return DataFormatUtils.getSubBytesArr(this.rawContent,this.separatorPosition,this.rawContent.length);
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
}
