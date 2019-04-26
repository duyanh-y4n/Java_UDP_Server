package com.y4n.Utils;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

public class NetworkUtils {
    private static ArrayList<InetAddress> localMachineAddresses = getMachineNetWorkInterfaceIpAddress();

    public static ArrayList<InetAddress> getMachineNetWorkInterfaceIpAddress() {
        ArrayList<InetAddress> localMachineAddresses = new ArrayList<InetAddress>();
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
                    localMachineAddresses.add(addr);
                }
            }
            return localMachineAddresses;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printLocalMachineAddresses() {
        for (InetAddress addressDisplayName :
                localMachineAddresses) {
            System.out.println("IP Adress: "
                    + addressDisplayName.getHostAddress());
            System.out.println(addressDisplayName.getClass());
        }
    }

    public static InetAddress getLocalHostAddress() {
        for (InetAddress addressDisplayName :
                localMachineAddresses) {
            if (addressDisplayName.getClass()==Inet4Address.class) return addressDisplayName;
        }
        try {
            return Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return Inet4Address.getLoopbackAddress();
        }
    }

    public static String getLocalHostIP() {
        return getLocalHostAddress().getHostAddress();//Network interface = wlp13s0
    }

    public static InetAddress initMulticastAddress(String ip) throws UnknownHostException {
        return InetAddress.getByName(ip);
    }

    public static byte[] getLocalIPAsRawBytes() {
        return getLocalHostAddress().getAddress();
    }
}
