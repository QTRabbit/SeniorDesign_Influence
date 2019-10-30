package e.qintto.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private View view;
    private boolean status = true;
    private static Canvas canvas;
    boolean menu, pagetwo, pagethree;
    String identification= "";
    int id_lenth= 0;

    public MainThread(SurfaceHolder surfaceHolder, View view){
        super();
        this.surfaceHolder = surfaceHolder;
        this.view = view;
    }


    @Override
    public void run(){
        while(status){
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.view.draw(canvas);
                }
                sleep(100);
            } catch(Exception e){

            }
            finally{
                if(canvas !=null)
                {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){
                        e.printStackTrace();;
                    }
                }
            }


        }

    }
}
