package ACT3_1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    public static void main(String[] args) throws IOException {
        //la création de la socket servuer
        ServerSocket sr=new ServerSocket(1234);
        System.out.println("le servuer commence a accepter des clients...");
        //le serveurMultithread commence a accepte beaucoup de clients a la fois
        while(true){
            Socket s=sr.accept();
            System.out.println(s.getRemoteSocketAddress());
            clientproc p=new clientproc(s);
            p.start();
            //ouvrir le flux de communication
            OutputStream o=s.getOutputStream();
            //ouvrir le flux de de triement
            o.write(clientproc.nbclient);

        }

    }
}
