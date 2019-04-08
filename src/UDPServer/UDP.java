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
    protected InetAddress hostAddress;
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
        this.datagramSocket = new DatagramSocket(this.port);
    }

    public void setupListener(int port) throws SocketException {
        this.port = port;
        this.datagramSocket = new DatagramSocket(this.port);
    }
}
