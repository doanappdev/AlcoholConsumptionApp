package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class ButtonClass extends Sprite
{

    private int currentFrame;

    public boolean silouhette;

    public ButtonClass(GameView gameView, int id, Bitmap bmp, final int columns, final int rows)
    {
        super(gameView, id, bmp, columns, rows);
        paint = new Paint();
        paint.setARGB(255, 255, 0, 0);
        paint.setStyle(Paint.Style.FILL);

    }

    //@Override
    public void update() {
        currentFrame = id;
        if(silouhette) currentFrame += 5;
    }

    @Override
    public void onDraw(Canvas canvas) {
        update();
        srcX = currentFrame%BMP_COLUMNS * width;
        srcY = currentFrame/BMP_COLUMNS * height;
        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        dst = new RectF(x, y, x + width * 2, y + height * 2);

        //filter = new LightingColorFilter(paint.getColor(), 0);
        //paint.setColorFilter(filter);

        canvas.drawBitmap(bmp, src, dst, paint);
    }

    public float getYLimit(){ return y; } //y + height

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width * 2 && y2 > y && y2 < y + height * 2;
    }

    public boolean isIntersecting(RectF sample){
        return dst.intersect(sample);
    }
}