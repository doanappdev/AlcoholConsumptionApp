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
    public boolean silouhette;
    private Paint silouhetteP;

    public ButtonClass(GameView gameView, int id, Bitmap bmp, final int columns, final int rows)
    {
        super(gameView, id, bmp, columns, rows);

        silouhette = true;

        silouhetteP = new Paint();
        silouhetteP.setStyle(Paint.Style.FILL);
        silouhetteP.setARGB(255, 255, 255, 255);
        filter = new LightingColorFilter(paint.getColor(), 0);
        silouhetteP.setColorFilter(filter);

        switch(id){
            case 0:setColor(255,200,200,0);break;
            case 1:setColor(255,255,0,0);break;
            case 2:setColor(255,0,255,0);break;
            case 3:setColor(255,64,64,255);break;
            default:setColor(255,255,255,255);break;
        }
        dst = new RectF(x, y, x + width * 2, y + height * 2);
    }

    //@Override
    /*public void update() {
        if(silouhette)
        else
    }*/

    @Override
    public void onDraw(Canvas canvas){
        dst.set(x, y, x + width * 2, y + height * 2);
        if(silouhette)
            canvas.drawBitmap(bmp, src, dst, silouhetteP);
        else{
            if(id > 3)
                canvas.drawBitmap(bmp, src, dst, null);
            else
                canvas.drawBitmap(bmp, src, dst, paint);
        }
    }

    public float getYLimit(){ return y; } //y + height

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width * 2 && y2 > y && y2 < y + height * 2;
    }

    public boolean isIntersecting(RectF sample){
        return dst.intersect(sample);
    }
}