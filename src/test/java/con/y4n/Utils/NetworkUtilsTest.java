package con.y4n.Utils;

import com.y4n.Utils.NetworkUtils;
import org.junit.Assert;
import org.junit.Test;

public class NetworkUtilsTest {

    @Test
    public void itShouldReturnCorrectIPAsBytesArrayWhenGetLocalIpAsRawBytes() {
        String IpString = NetworkUtils.getLocalHostIP();
        NetworkUtils.printLocalMachineAddresses();
        String[] IPOctets = IpString.split("\\.");
        byte[] IPOctetsRaw = new byte[4];
        for (int i = 0; i < 4; i++) {
            IPOctetsRaw[i] = (byte) Integer.parseInt(IPOctets[i]);
        }
        System.out.println(IPOctetsRaw[0]);
        Assert.assertArrayEquals(IPOctetsRaw,NetworkUtils.getLocalIPAsRawBytes());
    }
}
