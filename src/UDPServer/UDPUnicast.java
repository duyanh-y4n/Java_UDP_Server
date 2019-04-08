package UDPServer;

import Utils.DataFormatUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UDPUnicast extends UDP{

    public UDPUnicast() throws IOException {
        super();
        this.setupUnicastSocket();
    }

    public UDPUnicast(int port) throws SocketException, UnknownHostException {
        super(port);
        this.setupUnicastSocket(port);
    }

    public byte[] getPacketAsRawBytes() throws IOException {
        DatagramPacket receivedPacket = this.getPacket();
        return receivedPacket.getData();
    }

    public byte[] getPacketAsRawBytes(int bufferLength) throws IOException {
        DatagramPacket receivedPacket = this.getPacket(bufferLength);
        return receivedPacket.getData();
    }

    public String getPacketAsStr() throws IOException {
        return this.cleanStringPacket(new String(this.getPacketAsRawBytes()));
    }

    public String getPacketAsStr(int bufferLength) throws IOException {
        return this.cleanStringPacket(new String(this.getPacketAsRawBytes(bufferLength)));
    }

    public List<Integer> getPacketAsIntList() throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketAsRawBytes());
    }

    public List<Integer> getPacketAsIntList(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketAsRawBytes(bufferLength));
    }

    public String getPacketAndConvertToHEXStr() throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketAsRawBytes());
    }

    public String getPacketAndConvertToHEXStr(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketAsRawBytes(bufferLength));
    }

    public List<String> getPacketAndConvertToHEXCharList() throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketAndConvertToHEXStr());
    }

    public List<String> getPacketAndConvertToHEXCharList(int bufferLength) throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketAndConvertToHEXStr(bufferLength));
    }

    public void sendPacket(byte[] packetContent, String destinationIP, int port) throws IOException {
        InetAddress destinationAddress = InetAddress.getByName(destinationIP);
        DatagramPacket sentPacket = new DatagramPacket(packetContent, packetContent.length, destinationAddress, port);
        this.datagramSocket.send(sentPacket);
    }

    public void sendPacket(int[] packetContent, String destinationIP, int port) throws IOException {
        this.sendPacket(DataFormatUtils.intArrToByteArr(packetContent), destinationIP, port);
    }

    public void sendPacketAsStr(String packetContent, String destinationIP, int port){

    }

    public void sendPacketAsHEXs(ArrayList<String> packetContent, String destinationIP, int port){

    }


    public String cleanStringPacket(String recieveDataStr) {
        byte[] data = recieveDataStr.getBytes();
        int pos = data.length - 1;
        while (pos != 0 && data[pos] == 0) {
            pos--;
        }
        return new String(data, 0, pos + 1);
    }
}
