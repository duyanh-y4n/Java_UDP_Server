package Utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;

public class NetworkUtils {
    private static HashMap<String, InetAddress> localMachineAddresses = getMachineNetWorkInterfaceIpAddress();

    public static HashMap<String,InetAddress> getMachineNetWorkInterfaceIpAddress() {
        String ip;
        HashMap<String, InetAddress> localMachineAddresses = new HashMap<String, InetAddress>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out loopback 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    localMachineAddresses.put(iface.getDisplayName(), addr);
                    ip = addr.getHostAddress();
                }
            }
            return localMachineAddresses;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printLocalMachineAddresses() {
        for (String addressDisplayName :
                localMachineAddresses.keySet()) {
            System.out.println("Network interface: " + addressDisplayName + " - IP Adress: "
                    + localMachineAddresses.get(addressDisplayName).getHostAddress());
        }
    }

    public static HashMap<String, InetAddress> getLocalMachineAddresses() {
        return localMachineAddresses;
    }

    public static InetAddress getLocalHostAddress() {
        return localMachineAddresses.get("wlp13s0");//Network interface = wlp13s0
    }

    public static String getLocalHostIP() {
        return getLocalHostAddress().getHostAddress();//Network interface = wlp13s0
    }

    public static InetAddress initMulticastAddress(String ip) throws UnknownHostException {
        return InetAddress.getByName(ip);
    }
}
