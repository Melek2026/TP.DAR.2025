package ACT3_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public  static void main(String[] args) throws IOException, ClassNotFoundException {
        //lancer le socket client
        Socket cl=new Socket("192.168.1.15",1234);
        System.out.println("je suis connecté au serveur");
        //ouvrir le flux de communication pour donner les paramétrs de l'opération
        InputStream in=cl.getInputStream();
        BufferedReader bi = new BufferedReader(new InputStreamReader(in));
        String s=bi.readLine();
        System.out.println(s);
        //envoyer les paramétres de l'opération
        Scanner sc = new Scanner(System.in);
        System.out.println("donner le premier operand");
        int op1=sc.nextInt();
        System.out.println("donner le deuxiéme operand");
        int op2=sc.nextInt();
        System.out.println("donner l'opération");
        sc.nextLine();
        String oprat=sc.nextLine();
        while(!oprat.equals("+")&& !oprat.equals("-")&& !oprat.equals("*")&&!oprat.equals("/"))
        {
            System.out.println("donner un operateur valide");
            oprat=sc.nextLine();
        }
        operation op=new operation(op1,op2,oprat);
        //envoyer l'objet par le flux de communication au serveur
        OutputStream out=cl.getOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(out);
        oos.writeObject(op);
        //recevoir l'objet pour afficher le résultat
        InputStream is = cl.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        operation opr= (operation) ois.readObject();
        System.out.println(opr.opd1+opr.op+opr.opd2+"="+opr.res);







    }
}
