package e.qintto.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
        thread.pagethreep1 = false;
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
                    thread.pagethreep1 = true;
                    client.inputstr = thread.identification;
                    client.execute();

                }


            }


        }
        else if(thread.pagethreep1 && e.getAction() == MotionEvent.ACTION_DOWN){
            thread.pagethreep1 = false;
            thread.pagethree = true;
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

        }
        else if(thread.pagethreep1){
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.heart));
            image.draw(c, 0, 0);
            inp = client.outputstr3;
            paint.setColor(Color.BLACK);
            paint.setTextSize(85);
            String rate = "", date = "", infect = "";
            String bodytemp = "", date2 = "";
            int counter = 0, breaks = 0;
            while(inp.charAt(counter) != '~'){
                if(inp.charAt(counter) == ' ') {
                    breaks += 1;
                    counter += 1;
                }
                if(breaks == 1){
                    rate += inp.charAt(counter);
                    counter += 1;
                }
                else if(breaks == 2){
                    date += inp.charAt(counter);
                    counter += 1;
                }
                else if(breaks == 3){
                    infect += inp.charAt(counter);
                    counter += 1;
                }
                else {
                    counter += 1;
                }

            }
            counter = 0;
            breaks = 0;
            inp = client.outputstr4;
            while(inp.charAt(counter) != '~'){
                if(inp.charAt(counter) == ' ') {
                    breaks += 1;
                    counter += 1;
                }
                if(breaks == 1){
                    bodytemp += inp.charAt(counter);
                    counter += 1;
                }
                else if(breaks == 2){
                    date2 += inp.charAt(counter);
                    counter += 1;
                }
                else {
                    counter += 1;
                }

            }

            c.drawText("Heart Rate: ", 280, 520, paint);
            c.drawText(rate, 830, 520, paint);
            c.drawText("Date of Measurement: ", 280, 670, paint);
            c.drawText(date, 870, 820, paint);
            c.drawText("Body Temperature: ", 280, 970, paint);
            c.drawText(bodytemp, 870, 1120, paint);
            c.drawText("Date of Measurement: ", 280, 1270, paint);
            c.drawText(date2, 870, 1420, paint);
        }
        else if(thread.pagethree){
            paint.setColor(Color.WHITE);
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.other));
            image.draw(c, 0, 0);
            float lastx = 300, lasty = 1820;

            Rect r = new Rect(300, 900, 1350, 1820);
            c.drawRect(r, paint);

            int counter = 0;
            inp = client.outputstr;

            //paint.setTextSize(50);
            //c.drawText(inp, 500, 300, paint);

            boolean time= true;
            String timehold = "", temphold = "";
            while(inp.charAt(counter) != '!'){
                timehold = "";
                temphold = "";
                while(inp.charAt(counter) != ' ' && time){
                    timehold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == ' '){
                    time = false;
                    counter +=1;
                }
                while(inp.charAt(counter) != '~'){
                    temphold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == '~'){
                    int pointx , pointy;
                    float point1 =(float) Double.parseDouble(timehold)*2500 +300;
                    float point2 =(((float) Double.parseDouble(temphold)) - 200000) * 8/100;
                    point2 = 1820- point2;
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.BLACK);
                    c.drawCircle(point1, point2, 8, paint);
                    paint.setStrokeWidth(5);
                    c.drawLine(lastx, lasty, point1, point2, paint);
                    lastx = point1;
                    lasty = point2;
                    counter += 1;
                    time = true;
                }
            }

            int graphcount = 0;
            paint.setColor(Color.rgb(54,47,47));
            while(graphcount != 50) {
                paint.setTextSize(50);
                c.drawCircle(300+ graphcount*25, 1820, 9, paint);
                c.drawText((String.valueOf(graphcount / 10)), 280 + graphcount*25, 1920, paint);
                graphcount += 10;

            }

            graphcount = -80000;
            int newcount = 200000;
            while(graphcount != 160000) {
                paint.setTextSize(50);
                c.drawCircle(300, 1820 - (graphcount + 80000) / 250, 9, paint);
                c.drawText(String.valueOf(newcount), 50, 1820 - (graphcount + 80000) / 250, paint);
                graphcount += 20000;
                newcount += 1000;

            }

            c.drawText("PPG Reading in Red Count", 20, 850, paint);
            c.drawText("Time in Seconds", 500, 2050, paint);


        }
        /*else if(thread.pagethree && thread.pagetwo){
            paint.setColor(Color.WHITE);
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.other));
            image.draw(c, 0, 0);
            float lastx = 200, lasty = 1800;

            Rect r = new Rect(200, 700, 1300, 1800);
            c.drawRect(r, paint);

            int counter = 0;
            inp = client.outputstr;
            boolean time= true;
            String timehold = "", temphold = "";
            while(inp.charAt(counter) != '!'){
                timehold = "";
                temphold = "";
                while(inp.charAt(counter) != ' ' && time){
                    timehold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == ' '){
                    time = false;
                    counter +=1;
                }
                while(inp.charAt(counter) != '~'){
                    temphold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == '~'){
                    int pointx , pointy;
                    //pointx = ((int)  Double.parseDouble(timehold))*3 +100;
                    //pointy = (int)  (Double.parseDouble(temphold) - 24)*100 + 500;
                    float point1 =(float) Double.parseDouble(timehold)*3 +200;
                    float point2 =((float) Double.parseDouble(temphold)-24)*100 +500;
                    point2 = 2000- point2;
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.BLACK);
                    c.drawCircle(point1, point2, 8, paint);
                    paint.setStrokeWidth(5);
                    c.drawLine(lastx, lasty, point1, point2, paint);
                    lastx = point1;
                    lasty = point2;
                    counter += 1;
                    time = true;
                }
            }

            //paint.setStyle(Paint.Style.STROKE);  // No filler
            int graphcount = 0;
            paint.setColor(Color.rgb(54,47,47));
            while(graphcount != 400) {
                paint.setTextSize(50);
                c.drawCircle(200+ graphcount*3, 1800, 9, paint);
                c.drawText(String.valueOf(graphcount), 180 + graphcount*3, 1900, paint);
                graphcount += 50;
            }
            graphcount = 22;
            while(graphcount != 32) {
                paint.setTextSize(50);
                c.drawCircle(200, 1700-(graphcount- 22)*100, 9, paint);
                c.drawText(String.valueOf(graphcount), 50, 1700-(graphcount- 22)*100, paint);
                graphcount += 1;
            }
            c.drawText("Temperature in Celcius", 20, 650, paint);
            c.drawText("Time in Nanoseconds", 500, 2050, paint);
            c.drawText("Click AnyWhere to Continue", 500, 2150, paint);
        } */
        else{
            paint.setColor(Color.WHITE);
            image.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.othertwo));
            image.draw(c, 0, 0);
            float lastx = 300, lasty = 1820;

            Rect r = new Rect(300, 900, 1350, 1820);
            c.drawRect(r, paint);

            int counter = 0;
            inp = client.outputstr2;
            boolean time= true;
            String timehold = "", temphold = "";
            while(inp.charAt(counter) != '!'){
                timehold = "";
                temphold = "";
                while(inp.charAt(counter) != ' ' && time){
                    timehold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == ' '){
                    time = false;
                    counter +=1;
                }
                while(inp.charAt(counter) != '~'){
                    temphold += inp.charAt(counter);
                    counter += 1;
                }
                if(inp.charAt(counter) == '~'){
                    int pointx , pointy;
                    float point1 =(float) Double.parseDouble(timehold)*2500 +300;
                    float point2 =((float) Double.parseDouble(temphold))/250 +500;
                    point2 = 2000- point2;
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.BLACK);
                    c.drawCircle(point1, point2, 8, paint);
                    paint.setStrokeWidth(5);
                    c.drawLine(lastx, lasty, point1, point2, paint);
                    lastx = point1;
                    lasty = point2;
                    counter += 1;
                    time = true;
                }
            }

            int graphcount = 0;
            paint.setColor(Color.rgb(21,21,21));
            while(graphcount != 50) {
                paint.setTextSize(50);
                c.drawCircle(300+ graphcount*25, 1820, 9, paint);
                c.drawText((String.valueOf(graphcount / 10)), 280 + graphcount*25, 1920, paint);
                graphcount += 10;

            }

            graphcount = -80000;
            while(graphcount != 160000) {
                paint.setTextSize(50);
                c.drawCircle(300, 1820 - (graphcount + 80000) / 250, 9, paint);
                c.drawText(String.valueOf(graphcount), 50, 1820 - (graphcount + 80000) / 250, paint);
                graphcount += 20000;

            }

            c.drawText("ECG Reading", 20, 850, paint);
            c.drawText("Time in Seconds", 500, 2050, paint);

        }

    }


}
