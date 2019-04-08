package UDPServer;

import Utils.DataFormatUtils;

import java.io.IOException;
import java.net.*;
import java.util.List;

// TODO: write unit test


public class UDPUnicast extends UDP{

    public UDPUnicast() throws IOException {
        super();
        this.setupUnicastSocket();
    }

    public UDPUnicast(int port) throws SocketException, UnknownHostException {
        super(port);
        this.setupUnicastSocket(port);
    }

    //get packet body
    public byte[] getPacketBody() throws IOException {
        DatagramPacket receivedPacket = this.getPacket();
        return receivedPacket.getData();
    }

    public byte[] getPacketBody(int bufferLength) throws IOException {
        DatagramPacket receivedPacket = this.getPacket(bufferLength);
        return receivedPacket.getData();
    }

    public String getPacketBodyAsStr() throws IOException {
        return this.cleanStringInReceivedPacket(new String(this.getPacketBody()));
    }

    public String getPacketBodyAsStr(int bufferLength) throws IOException {
        return this.cleanStringInReceivedPacket(new String(this.getPacketBody(bufferLength)));
    }

    public List<Integer> getPacketBodyAsIntList() throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketBody());
    }

    public List<Integer> getPacketBodyAsIntList(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketBody(bufferLength));
    }

    public String getPacketBodyAndConvertToHEXStr() throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketBody());
    }

    public String getPacketBodyAndConvertToHEXStr(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketBody(bufferLength));
    }

    public List<String> getPacketBodyAndConvertToHEXCharList() throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketBodyAndConvertToHEXStr());
    }

    public List<String> getPacketBodyAndConvertToHEXCharList(int bufferLength) throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketBodyAndConvertToHEXStr(bufferLength));
    }

    //send packet body methods

    public void sendPacket(int[] packetContent, String destinationIP, int port) throws IOException {
        this.sendPacket(DataFormatUtils.intArrToByteArr(packetContent), destinationIP, port);
    }

    public void sendPacket(String packetContent, String destinationIP, int port) throws IOException {
        this.sendPacket(DataFormatUtils.StrToByteArr(packetContent), destinationIP, port);
    }

    public void sendPacket(List<String> packetContent, String destinationIP, int port) throws IOException {
        this.sendPacket(DataFormatUtils.HEXCharListToByteArr(packetContent), destinationIP, port);
    }


    public String cleanStringInReceivedPacket(String receiveDataStr) {
        byte[] data = receiveDataStr.getBytes();
        int pos = data.length - 1;
        while (pos != 0 && data[pos] == 0) {
            pos--;
        }
        return new String(data, 0, pos + 1);
    }
}
