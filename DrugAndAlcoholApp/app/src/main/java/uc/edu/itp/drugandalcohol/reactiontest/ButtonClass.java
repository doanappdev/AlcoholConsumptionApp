package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class ButtonClass extends Sprite {

    private int currentFrame;

    public boolean silouhette;

    public ButtonClass(GameView gameView, int id, Bitmap bmp,
                       final int columns, final int rows){
        super(gameView, id, bmp, columns, rows);

        x = (gameView.getWidth()/5) * id;
        y = gameView.getHeight() - 90;
        //dst = new Rect(x, y, x + width * 2, y + height * 2);
    }

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
        dst = new Rect(x, y, x + width * 2, y + height * 2);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public int getYLimit(){ return y; } //y + height

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width * 2 && y2 > y && y2 < y + height * 2;
    }

    public boolean isIntersecting(Rect sample){
        return dst.intersect(sample);
    }
}