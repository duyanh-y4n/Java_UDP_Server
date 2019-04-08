package UDPServer;

import Utils.DataFormatUtils;

import java.io.IOException;
import java.net.*;

public class UDPMuticaster {
    private final int DEFAULT_BUFFER_LENGTH = 1024;
    private final int DEFAULT_MULTICAST_PORT = 8081;
    private final String DEFAULT_MULTICAST_IP = "224.0.0.0";
    private int multicastPort;
    private InetAddress multicastAddress;
    private MulticastSocket multicastSocket;

    public UDPMuticaster() throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(DEFAULT_MULTICAST_PORT);
    }

    public UDPMuticaster(int multicastPort) throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(multicastPort);
    }

    public UDPMuticaster(String multicastIP) throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(multicastIP);
        this.multicastSocket = new MulticastSocket(DEFAULT_MULTICAST_PORT);
    }

    public UDPMuticaster(int multicastPort, String multicastIP) throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(multicastIP);
        this.multicastSocket = new MulticastSocket(multicastPort);
    }

    public void sendPacket(byte[] buffer) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.multicastAddress, this.multicastPort);
        this.multicastSocket.send(packet);
    }

    public void sendPacket(int[] buffer) throws IOException {
        DatagramPacket packet = new DatagramPacket(DataFormatUtils.intArrToByteArr(buffer), buffer.length, this.multicastAddress, this.multicastPort);
        this.multicastSocket.send(packet);
    }
}
