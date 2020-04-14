import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server {

    public static void main(String[]args){

        //Server lytter på port 5050
        ServerSocket serverSocket = new ServerSocket(5050);

        //Kører et loop for at få client request
        while (true) {
            Socket s = null;


            try {
                //Socket objektet modtager inkommende client requests

                s = serverSocket.accept();

                System.out.println("En ny client er connected : " + s);

                //tager input og out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigner ny thread til denne client");


                // herunder laves et nyt thread objekt

                Thread t = new ClientHandler(s, dis, dos);

                //Start metoden

                t.start();
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }


    }
}

class ClientHandler extends Thread {

    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    //Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String received;
        String toreturn;

        while (true) {

            try {
                //Spørger user hvad den vil
              dos.writeUTF("hvad vil du? [Date : Time]..\n" + "Skrive Exit for at terminere connection.");

                //Modtag svar fra client

                if(received.equals("Exit")){
                    System.out.println("Client" + this.s + " Send Exit..");
                    System.out.println("Connection lukkes.");
                    this.s.close();
                    System.out.println("Connection er lukket");
                    break;
                }

                //Laver date objekt
                Date date = new Date();

                } catch (IOException e){

                 e.printStackTrace();
            }


        }
        try{
            //Lukker resourcer
            this.dis.close();
            this.dos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


