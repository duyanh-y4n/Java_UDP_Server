import UDPServer.UDPListener;
import Utils.DataFormatUtils;

import java.io.IOException;
import java.util.Arrays;

public class Test_listen_unicast {
    public static void main(String[] args) throws IOException {
        UDPListener udpListener = new UDPListener();

        System.out.println("Host IP: " + udpListener.getHostIPAddress());

        while (true) {
            byte[] data = udpListener.getPacketAsRawBytes(8);
            System.out.println("received: " + Arrays.toString(data));
            System.out.println("in Hex form: " + DataFormatUtils.byteArrToHEXCharList(data));
            System.out.println("String form: " + DataFormatUtils.byteArrToStr(data));
        }
    }
}
