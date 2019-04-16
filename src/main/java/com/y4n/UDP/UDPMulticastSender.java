package com.y4n.UDP;

import com.y4n.Utils.DataFormatUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

// TODO: write unit test

public class UDPMulticastSender extends UDP{

    public UDPMulticastSender() throws IOException {
        super();
        this.setupMulticastSocket();
    }

    public UDPMulticastSender(int port) throws IOException {
        super(port);
        this.setupMulticastSocket(port);
    }

    public InetAddress getMulticastAddress() {
        return this.multicastAddress;
    }

    //more send packet methods
    public void sendPacket(byte[] packetContent) throws IOException {
        this.sendMulticastPacket(packetContent);
    }

    public void sendPacket(int[] packetContent) throws IOException {
        this.sendMulticastPacket(DataFormatUtils.intArrToByteArr(packetContent));
    }

    public void sendPacket(String packetContent) throws IOException {
        this.sendMulticastPacket(DataFormatUtils.StrToByteArr(packetContent));
    }

    public void sendPacket(List<String> packetContent) throws IOException {
        this.sendMulticastPacket(DataFormatUtils.HEXCharListToByteArr(packetContent));
    }
}
