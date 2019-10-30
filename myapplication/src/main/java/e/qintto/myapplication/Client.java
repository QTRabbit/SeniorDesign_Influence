package e.qintto.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, Void> {


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
            //);
            outToServer.println("HELLO DARE");
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
