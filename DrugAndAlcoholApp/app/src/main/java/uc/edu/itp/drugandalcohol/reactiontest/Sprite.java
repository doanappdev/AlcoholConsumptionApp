package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract class Sprite {

    protected int BMP_ROWS;
    protected int BMP_COLUMNS;

    protected int x;
    protected int y;

    protected GameView gameView;
    protected Bitmap bmp;

    protected int id;

    protected int width;
    protected int height;
    protected int srcX;
    protected int srcY;
    protected Rect src;
    protected Rect dst;

    public Sprite(GameView gameView, int id, Bitmap bmp, final int columns, final int rows) {
        this.gameView = gameView;
        this.id = id;
        this.bmp = bmp;

        BMP_COLUMNS = columns;
        BMP_ROWS = rows;

        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    abstract void onDraw(Canvas canvas);
    abstract void update();
}
