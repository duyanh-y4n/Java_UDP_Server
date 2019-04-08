package UDPServer;

import Utils.DataFormatUtils;

import java.io.IOException;
import java.net.InetAddress;

public class UDPMulticast extends UDP{

    public UDPMulticast() throws IOException {
        super();
        this.setUpMulticastSocket();
    }

    public UDPMulticast(int port) throws IOException {
        super(port);
        this.setUpMulticastSocket(port);
    }



    public void sendPacket(int[] packetContent) throws IOException {
        this.sendMulticastPacket(DataFormatUtils.intArrToByteArr(packetContent));
    }


    public InetAddress getMulticastAddress() {
        return this.multicastAddress;
    }
}
