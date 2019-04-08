package UDPServer;

import Utils.DataFormatUtils;
import Utils.NetworkUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UDPListener extends UDP{
//    private final int DEFAULT_BUFFER_LENGTH = 1024;
//    private final int DEFAULT_LISTEN_PORT = 8080;
//    private final String DEFAULT_MULTICAST_IP = "224.0.0.0";
//    private final int DEFAULT_MULTICAST_PORT = 8080;
//    private int port;
//    private InetAddress hostAddress;
//    private DatagramSocket datagramSocket;

    public UDPListener() throws IOException {
//        this.port = DEFAULT_LISTEN_PORT;
//        this.datagramSocket = new DatagramSocket(this.port);
//        this.hostAddress = NetworkUtils.getLocalHostAddress();
        super();
        super.setupListener();
    }

    public UDPListener(int port) throws SocketException, UnknownHostException {
//        this.port = port;
//        this.datagramSocket = new DatagramSocket(this.port);
//        this.hostAddress = NetworkUtils.getLocalHostAddress();
        super(port);
        super.setupListener();
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
        return this.cleanStringPacket(new String(this.getPacketAsRawBytes()));
    }

    public String getPacketAsStr(int bufferLength) throws IOException {
        return this.cleanStringPacket(new String(this.getPacketAsRawBytes(bufferLength)));
    }

    public List<Integer> getPacketAsInts() throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketAsRawBytes());
    }

    public List<Integer> getPacketAsInts(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToIntList(this.getPacketAsRawBytes(bufferLength));
    }

    public String getPacketAndConvoertToHEXStr() throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketAsRawBytes());
    }

    public String getPacketAndConvoertToHEXStr(int bufferLength) throws IOException {
        return DataFormatUtils.byteArrToHEXStr(this.getPacketAsRawBytes(bufferLength));
    }

    public List<String> getPacketAndConvertToHEXs() throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketAndConvoertToHEXStr());
    }

    public List<String> getPacketAndConvertToHEXs(int bufferLength) throws IOException {
        return DataFormatUtils.HEXStrToHEXCharList(this.getPacketAndConvoertToHEXStr(bufferLength));
    }

    public void sendPacketAsByte(byte[] packetContent, String destinationIP, int port) throws IOException {
        InetAddress destinationAddress = InetAddress.getByName(destinationIP);
        DatagramPacket sentPacket = new DatagramPacket(packetContent, packetContent.length,destinationAddress,port);
        this.datagramSocket.send(sentPacket);
    }

    public void sendPacketAsInt(int[] packetContent, String destinationIP, int port){

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
