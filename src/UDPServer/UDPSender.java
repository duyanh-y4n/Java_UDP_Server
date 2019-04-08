package UDPServer;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender extends UDP{

    public UDPSender() throws IOException {
        super();
        this.setUpMulticastSender();
    }

    public UDPSender(int port) throws IOException {
        super(port);
        this.setUpMulticastSender(port);
    }

    public void sendPacketAsRawBytes(byte[] packetContent, String destinationIP, int port) throws IOException {

    }


}
