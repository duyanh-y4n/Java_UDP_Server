import UDPServer.UDPUnicast;
import Utils.DataFormatUtils;

import java.io.IOException;
import java.util.Arrays;

public class Test_listen_unicast {
    public static void main(String[] args) throws IOException {
        UDPUnicast udpListener = new UDPUnicast();

        System.out.println("Host IP: " + udpListener.getHostIPAddress());

        while (true) {
            byte[] data = udpListener.getPacketBody(8);
            System.out.println("received: " + Arrays.toString(data));
            System.out.println("in Hex form: " + DataFormatUtils.byteArrToHEXCharList(data));
            System.out.println("String form: " + DataFormatUtils.byteArrToStr(data));
        }
    }
}
