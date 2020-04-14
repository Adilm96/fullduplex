import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[]args){

        try{
            Scanner sc = new Scanner(System.in);

            //får localhos adressen
            InetAddress ip = InetAddress.getByName("localhost");

            //Skaber en connection med server porten 5050
            Socket s = new Socket(ip,5050);

            //Input og output stream
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            //Følgende loop performer bytte af info mellem client og clienthandler

            while (true) {

                System.out.println(dis.readUTF());
                String tosend = sc.nextLine();
                dos.writeUTF(tosend);

                //Hvis clienten sender exit så lukkes denne connection og loop afbrydes
                if (tosend.equals("Exit")) {
                    System.out.println("Connection lukkes");
                    s.close();
                    System.out.println("Connection er lukket");
                    break;


                    //printer dato og tid
                    String received = dis.readUTF();
                    System.out.println(received);
                }

            }
                //lukker resourcerne
                sc.close();
                dis.close();
                dos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
