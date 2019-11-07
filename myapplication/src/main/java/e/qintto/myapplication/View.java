package e.qintto.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;


public class View extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Images image = new Images();
    Paint paint = new Paint();
    int pointx, pointy;
    Client client = new Client();
    String inp;


    public View(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){


    }



    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.start();
        thread.menu = true;
        thread.pagetwo = false;
        thread.pagethree = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int event = e.getAction();
        pointx = (int) e.getX();
        pointy = (int) e.getY();
        if(thread.menu && e.getAction() == MotionEvent.ACTION_DOWN){
            if((pointx > 250) && (pointx < 1225) && (pointy >1475) && (pointy < 1825)){
                thread.menu = false;
                thread.pagetwo = true;
            }
        }
        else if (thread.pagetwo && e.getAction() == MotionEvent.ACTION_DOWN){
            /*The following takes the 8 digit user input*/
            if((pointx > 310) && (pointx < 520) && (pointy >870) && (pointy < 1100)){
                thread.identification += "1";
                thread.id_lenth += 1;
            }
            else if((pointx > 620) && (pointx < 830) && (pointy >870) && (pointy < 1100)){
                thread.identification += "2";
                thread.id_lenth += 1;
            }
            else if((pointx > 930) && (pointx < 1140) && (pointy >870) && (pointy < 1100)){
                thread.identification += "3";
                thread.id_lenth += 1;
            }
            else if((pointx > 310) && (pointx < 520) && (pointy >1190) && (pointy < 1420)){
                thread.identification += "4";
                thread.id_lenth += 1;
            }
            else if((pointx > 620) && (pointx < 830) && (pointy >1190) && (pointy < 1420)){
                thread.identification += "5";
                thread.id_lenth += 1;
            }
            else if((pointx > 930) && (pointx < 1140) && (pointy >1190) && (pointy < 1420)){
                thread.identification += "6";
                thread.id_lenth += 1;
            }
            else if((pointx > 310) && (pointx < 520) && (pointy >1510) && (pointy < 1740)){
                thread.identification += "7";
                thread.id_lenth += 1;
            }
            else if((pointx > 620) && (pointx < 830) && (pointy >1510) && (pointy < 1740)){
                thread.identification += "8";
                thread.id_lenth += 1;
            }
            else if((pointx > 930) && (pointx < 1140) && (pointy >1510) && (pointy < 1740)){
                thread.identification += "9";
                thread.id_lenth += 1;
            }
            else if((pointx > 310) && (pointx < 520) && (pointy >1830) && (pointy < 2060)){
                thread.identification += "0";
                thread.id_lenth += 1;
            }
            else if((pointx > 620) && (pointx < 830) && (pointy >1830) && (pointy < 2060)){
                if(thread.id_lenth != 0){
                    thread.id_lenth -= 1;
                    String strcopy;
                    //for (int i = 0; i < thread.id_lenth; i++) {
                    strcopy = thread.identification.substring(0, thread.id_lenth);
                    thread.identification = strcopy;

                    //}
                }
            }
            else if((pointx > 930) && (pointx < 1140) && (pointy >1830) && (pointy < 2060)){
                if(thread.id_lenth == 8){
                    thread.pagetwo = false;
                    thread.pagethree = true;
                    client.inputstr = thread.identification;
                    client.execute();
                }


            }


        }
        else if(thread.pagethree && e.getAction() == MotionEvent.ACTION_DOWN){
            thread.pagethree = false;

        }



        return true;
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }


    @Override
    public void draw(Canvas c){
        super.draw(c);
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);

        //paint.setTextAlign(Paint.Align.CENTER)
        if(thread.menu) {
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.seniormenu));
            image.draw(c, 0, 0);
        }
        else if(thread.pagetwo) {
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.pagetwo));
            image.draw(c, 0, 0);


            //paint.setTextAlign(Paint.Align.CENTER);
            c.drawText(thread.identification, 350, 720, paint);

            /*
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.seniortest));
            image.draw(c, 310, 1190);
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.seniortest));
            image.draw(c, 520, 1420);   */


        }
        else{
            inp = client.outputstr;
            paint.setTextSize(70);
            c.drawText(inp, 100, 720, paint);
            inp = client.outputstr2;
            c.drawText(inp, 100, 1020, paint);

        }

    }


}
