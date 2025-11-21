package ACT3_1;

import java.io.InputStream;
import java.net.*;

public class client {

    public static void main(String[] args) throws Exception{
        //la création de socket coté client
        Socket c=new Socket("localhost",1234);
        System.out.println("je suis connecté");
        //ouvrir le flux de communication
        InputStream in=c.getInputStream();
        //ouvrir le flux de triement
        int nb=in.read();
        //affichier le numéro propre de client
        System.out.println("mon numéro de traitement coté serveur est :"+nb);


    }
}
