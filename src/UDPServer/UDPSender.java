package UDPServer;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender extends UDP{

    public UDPSender() throws IOException {

    }

    public UDPSender(int port) throws SocketException, UnknownHostException {
        super(port);
    }
}
