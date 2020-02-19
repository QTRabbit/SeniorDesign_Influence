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
    String outputstr = "", outputstr2 = "", outputstr3 = "", outputstr4 = "'";

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

            inputstr = Encryption(inputstr);
            PrintWriter outToServer = new PrintWriter( out, true);
            outToServer.println(inputstr);
            //sock.close();

            //Socket clisock = new Socket("169.254.174.11", 7000) ;


            DataInputStream dis =new DataInputStream(sock.getInputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            //outputstr=(String)dis.readUTF();

            outputstr3 = bf.readLine();
            outputstr3 = Decryption(outputstr3);
            outputstr4 = bf.readLine();
            outputstr4 = Decryption(outputstr4);
            outputstr = outputstr + bf.readLine();
            outputstr = Decryption(outputstr);
            outputstr2 = bf.readLine();
            outputstr2 = Decryption(outputstr2);

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


    public String Encryption(String vals){
        /*  Even index shift down 2, odd go up 4 */
        String bak = "";
        boolean even = true;
        int counter = 0;
        while(counter != 8){
            if(vals.charAt(counter) == '0' && even){
                bak += '8';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '0'){
                bak += '4';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '1' && even){
                bak += '9';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '1'){
                bak += '5';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '2' && even){
                bak += '0';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '2'){
                bak += '6';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '3' && even){
                bak += '1';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '3'){
                bak += '7';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '4' && even){
                bak += '2';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '4'){
                bak += '8';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '5' && even){
                bak += '3';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '5'){
                bak += '9';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '6' && even){
                bak += '4';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '6'){
                bak += '0';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '7' && even){
                bak += '5';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '7'){
                bak += '1';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '8' && even){
                bak += '6';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '8'){
                bak += '2';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '9' && even){
                bak += '7';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '9'){
                bak += '3';
                counter += 1;
                even = true;
            }
            else{
                bak += vals.charAt(counter);
                counter += 1;
                if(even) even = false;
                else even = true;
            }
        }
        return bak;
    }




    public String Decryption(String vals){
        /*  Even index shift up 2, odd go down 4 */
        String bak = "";
        boolean even = true;
        int counter = 0;
        while(vals.charAt(counter) != '!'){
            if(vals.charAt(counter) == '0' && even){
                bak += '2';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '0'){
                bak += '6';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '1' && even){
                bak += '3';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '1'){
                bak += '7';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '2' && even){
                bak += '4';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '2'){
                bak += '8';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '3' && even){
                bak += '5';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '3'){
                bak += '9';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '4' && even){
                bak += '6';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '4'){
                bak += '0';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '5' && even){
                bak += '7';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '5'){
                bak += '1';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '6' && even){
                bak += '8';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '6'){
                bak += '2';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '7' && even){
                bak += '9';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '7'){
                bak += '3';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '8' && even){
                bak += '0';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '8'){
                bak += '4';
                counter += 1;
                even = true;
            }
            else if(vals.charAt(counter) == '9' && even){
                bak += '1';
                counter += 1;
                even = false;
            }
            else if(vals.charAt(counter) == '9'){
                bak += '5';
                counter += 1;
                even = true;
            }
            else{
                bak += vals.charAt(counter);
                counter += 1;
                if(even) even = false;
                else even = true;
            }
        }
        bak += vals.charAt(counter);
        return bak;
    }
}
