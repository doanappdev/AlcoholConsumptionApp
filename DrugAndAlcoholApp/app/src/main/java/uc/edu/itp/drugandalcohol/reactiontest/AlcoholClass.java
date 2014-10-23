package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class AlcoholClass extends Sprite {

    private int xSpeed;
    private int ySpeed;
    private int endY;
    private int points;

    public boolean active;

    public AlcoholClass(GameView gameView, int id, Bitmap bmp, final int columns, final int rows) {
        super(gameView, id, bmp, columns, rows);

        active = false;
        srcX = id%BMP_COLUMNS * width;
        srcY = 0;
        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        endY = gameView.getHeight() + height;
    }

    public void reset(int xSpeed, int ySpeed, int id){
        if(this.id != id){
            this.id = id;
            srcX = id%BMP_COLUMNS * width;
            src = new Rect(srcX, srcY, srcX + width, srcY + height);
        }

        int g_width = gameView.getWidth();
        x = g_width*id/5 + g_width/10 - width;
        y = 0;
        //y = gameView.getHeight()/20;

        midX = x + width;//width/2;
        midY = y + height;//height/2;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        if(id < 4) active = true;

        setPoints();
    }

    public void ResetTNT(){
        y = 0;
        //y = gameView.getHeight()/20;
        active = true;
    }

    @Override
    protected void update() {
        y += ySpeed;
        midY += ySpeed;
        if (y > endY) active = false;
    }

    public void TNTUpdate() {
        if(active){
            y += ySpeed;
            if (y >= endY) {
                y = endY;
                active = false;
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(id < 4)update();
        dst = new RectF(x, y, x + width * 2, y + height * 2);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public void destroyTNT(){
        y = endY;
        active = false;
    }

    public int getPoints()
    {
        return points;
    }

    private void setPoints(){
        switch(id){
            case 4: points = -2000; break;
            default: points = 50 * (ySpeed - 1); break;
        }
    }

    public RectF getRect(){
        return dst;
    }
}
