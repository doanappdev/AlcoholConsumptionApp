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

        int g_width = gameView.getWidth();

        //x = 0.8 of game width * id * 1/(number of buttons-1)

        //The formula of x is calculated by having 80% of game
        //width times the id number of a button divided by 1 less
        //the number of buttons in total, plus 10% of game width,
        //minus the scaled width of the button divided by 2.
        x = g_width*id/5 + g_width/10 - width;

        Log.d("ButtonClass: X - ", String.valueOf(x));
        y = gameView.getHeight() - 90;
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