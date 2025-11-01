package TP4;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
public class client {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        System.out.println("donner votre nom");
        String nom = sc.nextLine();

        byte[] buf = nom.getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 1234);
        socket.send(dp);
        System.out.println("message envoyé vers le serveur");

        // Thread pour écouter les messages du serveur
        new Thread(() -> {
            try {
                DatagramSocket receiveSocket = new DatagramSocket(1235); // Port pour recevoir les broadcasts
                byte[] receiveBuffer = new byte[1024];

                while(true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    receiveSocket.receive(receivePacket);
                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Message du serveur: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Attendre un peu avant de fermer
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        socket.close();
    }
}
