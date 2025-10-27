package TP4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class serveur  {
    public static byte buffer[]=new byte[1024];
public static void main(String[] args) throws IOException {
    DatagramSocket socket=new DatagramSocket(1234);
    System.out.println("le servuer attend un client ...");
    //préparation de l'acceptation d'un client
    DatagramPacket p=new DatagramPacket(buffer,buffer.length);
    //l'acceptation d'un client
    socket.receive(p);
    String donn=new String(p.getData(),0,p.getLength());
    System.out.println("bonjour:"+donn+"et votre adresse est :"+p.getAddress() +"et son port est:"+p.getPort());


}
}
