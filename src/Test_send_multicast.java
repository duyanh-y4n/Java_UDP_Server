import UDPSubClass.UDPMuticaster;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test_send_multicast {
    public static void main(String[] args) throws IOException, InterruptedException {
        UDPMuticaster udpMuticaster = new UDPMuticaster();
        int[] data = new int[]{0x41, 0x42, 0x43, 0x44};
        while (true){
            udpMuticaster.sendPacket(data);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
