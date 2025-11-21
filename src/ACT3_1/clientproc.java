package ACT3_1;
import java.net.Socket;
public class clientproc extends Thread{
    Socket s;
    public static int nbclient;
    public clientproc(Socket s){
        nbclient++;
        this.s=s;

    }
    public void run(){
        System.out.println("je suis client numéro:"+clientproc.nbclient);

    }

}
