package UDPServer;

import Utils.NetworkUtils;

import java.io.IOException;
import java.net.*;

public class UDP {
    protected final int DEFAULT_BUFFER_LENGTH = 1024;
    protected final int DEFAULT_LISTEN_PORT = 8080;
    protected final String DEFAULT_MULTICAST_IP = "224.0.0.0";
    protected final int DEFAULT_MULTICAST_PORT = 8080;
    protected int port;
    protected int multicastPort;
    protected InetAddress hostAddress;
    protected InetAddress multicastAddress;
    protected DatagramSocket datagramSocket;
    protected MulticastSocket multicastSocket;


    // by default is as sender
    public UDP() throws IOException {
        this.port = DEFAULT_LISTEN_PORT;
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    public UDP(int port) throws SocketException, UnknownHostException {
        this.port = port;
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    public String getHostIPAddress() {
        return this.hostAddress.getHostAddress();
    }

    public byte[] makeDatabuffer() {
        return new byte[DEFAULT_BUFFER_LENGTH];
    }

    public byte[] makeDatabuffer(int bufferLength) {
        return new byte[bufferLength];
    }

    public void setupListener() throws SocketException {
        this.datagramSocket = new DatagramSocket(DEFAULT_LISTEN_PORT);
    }

    public void setupListener(int port) throws SocketException {
        this.port = port;
        this.datagramSocket = new DatagramSocket(this.port);
    }

    public void setUpMulticastSender() throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public void setUpMulticastSender(int multicastPort) throws IOException {
        this.multicastPort = multicastPort;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public DatagramPacket getPacket() throws IOException {
        byte[] receiveBuffer = makeDatabuffer(DEFAULT_BUFFER_LENGTH);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket;
    }

    public DatagramPacket getPacket(int bufferLength) throws IOException {
        byte[] receiveBuffer = makeDatabuffer(bufferLength);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket;
    };
}
