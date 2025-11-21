package ACT3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serveurMultithread {
    public static void main(String[] args) throws IOException{
        //création d'un socket serveur
        ServerSocket sc=new ServerSocket(1234);
        System.out.println("le serveur attend la connexion d'un client.....");
        //gérer plusieurs clinets
        while (true)
        {
            //l'acceptation d'un client
            Socket s=sc.accept();
            System.out.println("un client est connecté");
            clientproc proc=new clientproc(s);
            proc.start();
            System.out.println("le nombre d'opération éfeectué est:"+clientproc.nbop);

        }
    }
}
