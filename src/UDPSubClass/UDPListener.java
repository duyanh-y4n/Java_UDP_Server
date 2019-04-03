package UDPSubClass;

import Utils.DataFormatUtils;
import Utils.NetworkUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class UDPListener {
    private final int DEFAULT_BUFFER_LENGTH = 1024;
    private final int DEFAULT_LISTEN_PORT = 8081;
    private int port;
    private InetAddress hostAddress;
    private DatagramSocket datagramSocket;

    public UDPListener() throws IOException {
        this.port = DEFAULT_LISTEN_PORT;
        this.datagramSocket = new DatagramSocket(this.port);
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    public UDPListener(int port) throws SocketException, UnknownHostException {
        this.port = port;
        this.datagramSocket = new DatagramSocket(this.port);
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    public String getHostIPAddress(){
        return this.hostAddress.getHostAddress();
    }

    public byte[] makeDatabuffer() {
        return new byte[DEFAULT_BUFFER_LENGTH];
    }

    public byte[] makeDatabuffer(int bufferLength) {
        return new byte[bufferLength];
    }

    public byte[] getPacketAsRawBytes() throws IOException {
        byte[] receiveBuffer = makeDatabuffer(DEFAULT_BUFFER_LENGTH);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket.getData();
    }

    public byte[] getPacketAsRawBytes(int bufferLength) throws IOException {
        byte[] receiveBuffer = makeDatabuffer(bufferLength);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket.getData();
    }

    public String getPacketAsStr() throws IOException {
        return this.cleanString(new String(this.getPacketAsRawBytes()));
    }

    public String getPacketAsStr(int bufferLength) throws IOException {
        return this.cleanString(new String(this.getPacketAsRawBytes(bufferLength)));
    }

    public int[] getPacketAsInts() throws IOException {
        return DataFormatUtils.bytesToIntArr(this.getPacketAsRawBytes());
    }

    public int[] getPacketAsInts(int bufferLength) throws IOException {
        return DataFormatUtils.bytesToIntArr(this.getPacketAsRawBytes(bufferLength));
    }

    public String getPacketAndConvoertToHEXStr() throws IOException {
        return DataFormatUtils.bytesToHEXs(this.getPacketAsRawBytes());
    }

    public String getPacketAndConvoertToHEXStr(int bufferLength) throws IOException {
        return DataFormatUtils.bytesToHEXs(this.getPacketAsRawBytes(bufferLength));
    }

    public ArrayList<String> getPacketAndConvertToHEXs() throws IOException {
        return DataFormatUtils.HEXStrToHEXArrList(this.getPacketAndConvoertToHEXStr());
    }

    public ArrayList<String> getPacketAndConvertToHEXs(int bufferLength) throws IOException {
        return DataFormatUtils.HEXStrToHEXArrList(this.getPacketAndConvoertToHEXStr(bufferLength));
    }

    public String cleanString(String recieveDataStr) {
        byte[] data = recieveDataStr.getBytes();
        int pos = data.length - 1;
        while (pos != 0 && data[pos] == 0) {
            pos--;
        }
        return new String(data, 0, pos + 1);
    }
}
