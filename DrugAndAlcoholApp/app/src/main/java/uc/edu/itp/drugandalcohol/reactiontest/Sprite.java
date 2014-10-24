package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

abstract class Sprite {

    public float midX;
    public float midY;

    protected int BMP_ROWS;
    protected int BMP_COLUMNS;

    protected float x;
    protected float y;

    protected GameView gameView;
    protected Bitmap bmp;

    protected int id;

    protected int width;
    protected int height;
    protected int srcX;
    protected int srcY;
    protected Rect src;
    protected RectF dst;

    protected Paint paint;
    protected ColorFilter filter;

    public Sprite(GameView gameView, int id, Bitmap bmp, final int columns, final int rows) {
        this.gameView = gameView;
        this.id = id;
        this.bmp = bmp;

        BMP_COLUMNS = columns;
        BMP_ROWS = rows;

        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        srcX = id%columns * width;
        srcY = id/columns * height;
        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        dst = new RectF(x, y, x + width * 2, y + height * 2);
    }

    abstract void onDraw(Canvas canvas);

    //abstract void update();

    public void setPosX(float x){this.x = x;}
    public void setPosY(float y){this.y = y;}

    public void setColor(int a, int r, int g, int b){
        paint.setARGB(a, r, g, b);
        filter = new LightingColorFilter(paint.getColor(), 0);
        paint.setColorFilter(filter);
    }
}
