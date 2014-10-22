package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class ButtonClass extends Sprite {

    private int currentFrame;

    public boolean silouhette;

    public ButtonClass(GameView gameView, int id, Bitmap bmp,
                       final int columns, final int rows){
        super(gameView, id, bmp, columns, rows);
    }

    //1024 * 0.2 = 204.8

    //1024 * 0.2 = 204.8 * 4 = 819.2 * 0.2 = 256;
    //1024 * 0.25 = 256;

    //id-0 = 50
    //id-5 = 1024 - 50 = 974

    @Override
    public void update() {
        currentFrame = id;
        if(silouhette) currentFrame += 5;
    }

    @Override
    public void onDraw(Canvas canvas) {
        update();
        srcX = currentFrame%BMP_COLUMNS * width;
        srcY = (int)(currentFrame/BMP_COLUMNS) * height;
        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        dst = new RectF(x, y, x + width * 2, y + height * 2);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public float getYLimit(){ return y; } //y + height

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width * 2 && y2 > y && y2 < y + height * 2;
    }

    public boolean isIntersecting(RectF sample){
        return dst.intersect(sample);
    }
}