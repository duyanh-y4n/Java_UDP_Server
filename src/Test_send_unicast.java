import UDPServer.UDPUnicast;
import Utils.DataFormatUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Test_send_unicast {
    public static void main(String[] args) throws IOException, InterruptedException {
        UDPUnicast udpListener = new UDPUnicast();
        UDPUnicast udpSender = new UDPUnicast(8081);

        System.out.println("Host IP: " + udpListener.getHostIPAddress());

        while (true) {
            byte[] dataSent = new byte[]{0x41, 0x42, 0x43, 0x44};
            System.out.println("sent: " + Arrays.toString(dataSent));
            System.out.println("in Hex form: " + DataFormatUtils.byteArrToHEXCharList(dataSent));
            System.out.println("String form: " + DataFormatUtils.byteArrToStr(dataSent));
            udpSender.sendPacket(dataSent,udpListener.getHostIPAddress(),udpListener.getPort());

            byte[] data = udpListener.getPacketAsRawBytes(8);
            System.out.println("received: " + Arrays.toString(data));
            System.out.println("in Hex form: " + DataFormatUtils.byteArrToHEXCharList(data));
            System.out.println("String form: " + DataFormatUtils.byteArrToStr(data));
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
