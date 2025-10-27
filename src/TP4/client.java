package TP4;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
public class client {
    public static void main(String[] args) throws IOException {
        //le client crée une socket UDP
        DatagramSocket socket=new DatagramSocket();
        //préparer un paquet UDP contenant le message,l'adresse de serveur et le port
        Scanner sc=new Scanner(System.in);
        System.out.println("donner votre nom");
        String nom=sc.nextLine();
        //préparer les donnéesa envoyer via le udp
        byte[] buf=nom.getBytes();
        DatagramPacket dp=new DatagramPacket(buf,buf.length, InetAddress.getByName("192.168.1.26"),1234);
        //envoyer les données
        socket.send(dp);
        System.out.println("message envoyé vers le servuer");
        //fermmer la socket
        socket.close();
    }




}
