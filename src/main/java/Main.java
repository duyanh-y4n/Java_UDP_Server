import com.y4n.UDP.UDP;
import com.y4n.Logik.Trafficsystem;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Trafficsystem Trafficsystem_Team2 = new Trafficsystem();

        UDP udp = new UDP(8888);
        System.out.println(udp.getMulticastIP());

        byte[] Buffer = new byte[8];


        while (true) {
            DatagramPacket Packet = udp.getPacket(8);
            if (Packet != null) {
                Buffer = Packet.getData();
                if (true/*(Buffer&0xFF00) == Header für Anmeldenachricht*/) {
                    Trafficsystem_Team2.add_Vehicle((byte) 1/*ID aus Nachricht*/);
                    //Bestätigung senden?
                } else if (true/*(Buffer&0xFF00) == Header für Statusnachricht*/) {
                    byte clearance = Trafficsystem_Team2.Process_vehicle_status((byte) 1, (byte) 1, (byte) 1, (byte) 1/*ID,position,direction,speed aus Nachricht*/);
                    //System.out.println(clearance);
                    byte[] message = new byte[8];
                    //message erstellen incl. Header
                    message[4] = clearance;
                    udp.sendPacket(message, Packet.getAddress().toString(), udp.getPort());
                }
            }
        }
    }
}