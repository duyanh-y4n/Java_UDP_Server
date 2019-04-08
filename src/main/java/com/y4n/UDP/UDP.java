package com.y4n.UDP;

import com.y4n.Utils.*;
import java.io.IOException;
import java.net.*;

// TODO: write unit test

public class UDP {
    protected final int DEFAULT_BUFFER_LENGTH = 1024;
    protected final int DEFAULT_LOCAL_PORT = 8080;
    protected final String DEFAULT_MULTICAST_IP = "224.0.0.0";
    protected final int DEFAULT_MULTICAST_PORT = 8080;
    protected int port; //local port
    protected int multicastPort;
    protected InetAddress hostAddress;
    protected InetAddress multicastAddress;
    protected DatagramSocket datagramSocket;
    protected MulticastSocket multicastSocket;


    // by default is as sender
    public UDP() throws IOException {
        this.port = DEFAULT_LOCAL_PORT;
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    public UDP(int localPort) throws SocketException, UnknownHostException {
        this.port = localPort;
        this.hostAddress = NetworkUtils.getLocalHostAddress();
    }

    // GETTER
    public int getPort() {
        return this.port;
    }

    public InetAddress getMulticastAddress() {
        return this.multicastAddress;
    }

    public InetAddress getHostAddress() {
        return this.hostAddress;
    }

    public int getMulticastPort() {
        try {
            if (multicastPort==0) throw new NullPointerException();
        } catch (NullPointerException e){
            System.out.println("Multicast is not setup");
            return 0;
        }
        return this.multicastPort;
    }

    public String getMulticastIP(){
        return this.multicastAddress.getHostAddress();
    }

    public String getHostIPAddress() {
        return this.hostAddress.getHostAddress();
    }

    /*CONFIGURATION METHODEN*/

    //Packet size preparation
    public byte[] makeDatabuffer() {
        return new byte[DEFAULT_BUFFER_LENGTH];
    }

    public byte[] makeDatabuffer(int bufferLength) {
        return new byte[bufferLength];
    }

    //Setup Unicast
    public void setupUnicastSocket() throws SocketException {
        this.datagramSocket = new DatagramSocket(DEFAULT_LOCAL_PORT);
    }

    public void setupUnicastSocket(int port) throws SocketException {
        this.port = port;
        this.datagramSocket = new DatagramSocket(this.port);
    }

    //Setup multicast
    public void setupMulticastSocket() throws IOException {
        this.multicastPort = DEFAULT_MULTICAST_PORT;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public void setupMulticastSocket(int multicastPort) throws IOException {
        this.multicastPort = multicastPort;
        this.multicastAddress = InetAddress.getByName(DEFAULT_MULTICAST_IP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public void setupMulticastSocket(String multicastIP, int multicastPort) throws IOException {
        this.multicastPort = multicastPort;
        this.multicastAddress = InetAddress.getByName(multicastIP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public void changeMulticastPort(int port) throws IOException {
        this.multicastPort = multicastPort;
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }

    public void changeMulticastAddress(String newMulticastIP) throws IOException {
        this.multicastAddress = InetAddress.getByName(newMulticastIP);
        this.multicastSocket = new MulticastSocket(this.multicastPort);
    }


    // get packet methods
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
    }

    public DatagramPacket getMulticastPacket() throws IOException {
        byte[] receiveBuffer = makeDatabuffer(DEFAULT_BUFFER_LENGTH);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket;
    }

    public DatagramPacket getMulticastPacket(int bufferLength, String MulticastIPSource, int port) throws IOException {
        byte[] receiveBuffer = makeDatabuffer(bufferLength);
        DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        this.datagramSocket.receive(receivedPacket);
        return receivedPacket;
    }

    //send packet methods
    public void sendPacket(byte[] packetContent, String destinationIP, int port) throws IOException {
        InetAddress destinationAddress = InetAddress.getByName(destinationIP);
        DatagramPacket sentPacket = new DatagramPacket(packetContent, packetContent.length, destinationAddress, port);
        this.datagramSocket.send(sentPacket);
    }

    public void sendMulticastPacket(byte[] packetContent) throws IOException {
        DatagramPacket sentPacket = new DatagramPacket(packetContent, packetContent.length,
                this.multicastAddress, this.multicastPort);
        this.multicastSocket.send(sentPacket);
    }
}
