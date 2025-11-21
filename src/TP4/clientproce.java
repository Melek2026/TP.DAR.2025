package TP4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class clientproce extends Thread {
    public DatagramSocket socket;
    public static byte buffer[] = new byte[1024];
    public List<InetAddress> clients;

    public clientproce(DatagramSocket socket, List<InetAddress> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        while(true) { // Ajouter une boucle pour traiter plusieurs messages
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(p);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            String donn = new String(p.getData(), 0, p.getLength());
            String msg = "bonjour:" + donn + " et votre adresse est :" + p.getAddress() + " et son port est:" + p.getPort();
            System.out.println(msg);

            // Ajouter le client s'il est nouveau
            synchronized(clients) {
                if(!clients.contains(p.getAddress())) {
                    clients.add(p.getAddress());
                    System.out.println("Nouveau client ajouté : " + p.getAddress());
                }
            }

            // Diffuser le message à TOUS les clients (pas seulement à l'expéditeur)
            synchronized(clients) {
                for (InetAddress clientAddr : clients) {
                    try {
                        String broadcastMsg = "Nouveau client: " + donn + " - Adresse: " + p.getAddress();
                        byte[] buf = broadcastMsg.getBytes();
                        DatagramPacket dp = new DatagramPacket(buf, buf.length, clientAddr, 1235); // Utiliser un port différent
                        socket.send(dp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Message diffusé à " + clients.size() + " clients");
            }

            // Réinitialiser le buffer pour le prochain message
            buffer = new byte[1024];
        }
    }
}