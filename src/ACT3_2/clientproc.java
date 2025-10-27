package ACT3_2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class clientproc extends Thread {
    static int nbop=0;
    Socket s;
    private static final Object verrou=new Object();

    public clientproc(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // la création de l'objet opération
            operation op;
            // ouvrir le flux de communication pour interroger le client des opérations
            OutputStream os = s.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);
            out.println("donner les paramétre de l'opération");

            // recevoir l'objet opération et ouvrir le flux de traitement
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            operation opr = (operation) ois.readObject();

            if (opr.op.equals("+")) {
                opr.res = opr.somme();
            }
            if (opr.op.equals("-")) {
                opr.res =opr.soustraction();
            }
            if (opr.op.equals("*")) {
                opr.res = opr.multiplication();
            }
            if (opr.op.equals("/")) {
                opr.res = opr.division();
            }

            // envoyer l'objet vers le client
            OutputStream out1 = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out1);
            oos.writeObject(opr);
           synchronized (verrou) {
               nbop++;
           }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
