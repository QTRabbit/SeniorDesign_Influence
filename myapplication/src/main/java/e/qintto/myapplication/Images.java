package e.qintto.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Images {

    private Bitmap image;

    public void setPic(Bitmap updated){image = updated;}

    public void draw(Canvas c, int x, int y){
        c.drawBitmap(image, x, y, null);
    }
}
