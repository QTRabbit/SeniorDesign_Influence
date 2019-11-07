package e.qintto.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, Void> {

    String inputstr = "";  //Input String
    String outputstr = "", outputstr2 = "";

    @Override
    protected Void doInBackground(Void...params){

        try{
            Socket sock = new Socket("169.254.174.11", 7000) ;
            OutputStream out = sock.getOutputStream();
            //sock.connect(new InetSocketAddress("192.168.1.104", 7000));
            //ObjectOutput oos = new ObjectOutputStream(sock.getOutputStream());
            // oos.writeObject("hi there");
            // oos.close();


            //byte[] data = ("hello there").getBytes();
            //out.write(data);


            PrintWriter outToServer = new PrintWriter( out, true);
            outToServer.println(inputstr);
            //sock.close();

            //Socket clisock = new Socket("169.254.174.11", 7000) ;


            DataInputStream dis =new DataInputStream(sock.getInputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            //outputstr=(String)dis.readUTF();

            outputstr = outputstr + bf.readLine();
            outputstr2 = bf.readLine();
            //byte[] data = new byte[sock.getInputStream().available()];
            //outputstr = data.toString();
            System.out.println(outputstr);
            //outToServer.flush();
            //DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            //dos.writeUTF("hello there");
            sock.close();


        }catch(UnknownHostException e1){
            e1.printStackTrace();
        }
        catch (IOException except){
            except.printStackTrace();
        }
        catch(Exception e2){
            e2.printStackTrace();
        }
        return null;
    }
}
