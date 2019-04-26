import com.y4n.UDP.UDPMulticastSender;
import com.y4n.Utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        UDPMulticastSender udpMuticaster = new UDPMulticastSender();
//        System.out.println(udpMuticaster.getMulticastIP());
//        int[] data = new int[]{0x41, 0x42, 0x43, 0x44}; //send "ABCD" to multicast
//        while (true){
//            udpMuticaster.sendPacket("hello");
//            TimeUnit.SECONDS.sleep(1);
//        }
        System.out.println(NetworkUtils.getLocalHostAddress());
    }
}
