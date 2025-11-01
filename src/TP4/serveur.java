package TP4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class serveur {
    public static final List<InetAddress> clients =
            Collections.synchronizedList(new Vector<InetAddress>());

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1234);
        System.out.println("le serveur attend un client ...");

        // Créer un seul thread qui gère tous les clients
        clientproce processor = new clientproce(socket, clients);
        processor.start();

        // Garder le serveur actif
        try {
            processor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        socket.close();
    }
}
