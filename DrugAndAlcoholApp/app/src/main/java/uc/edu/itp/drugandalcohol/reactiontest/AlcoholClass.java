package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AlcoholClass extends Sprite {

    private int xSpeed;
    private int ySpeed;
    private int endY;
    private int points;

    public int midX;
    public int midY;
    public boolean active;

    public AlcoholClass(GameView gameView, int id, Bitmap bmp, final int columns, final int rows) {
        super(gameView, id, bmp, columns, rows);

        x = (gameView.getWidth()/5) * id;
        y = -100;
        xSpeed = 0;
        ySpeed = 3;
        midX = x + width;//width/2;
        midY = y + height;//height/2;
        active = false;

        srcX = id%BMP_COLUMNS * width;
        srcY = 0;
        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        endY = gameView.getHeight() + 100;
        setPoints();

        if(id > 3){
            points = -2000;
            y = -100;
        }
    }

    public void reset(int xSpeed, int ySpeed, int id){
        if(this.id != id){
            this.id = id;
            srcX = id%BMP_COLUMNS * width;
            src = new Rect(srcX, srcY, srcX + width, srcY + height);
        }

        x = (gameView.getWidth()/5) * id;
        y = -100;
        midX = x + width;//width/2;
        midY = y + height;//height/2;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        active = true;

        setPoints();
    }

    public void ResetTNT(){
        y = -100;
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
        dst = new Rect(x, y, x + width * 2, y + height * 2);
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
            case 0: points = 50 * (ySpeed - 2); break;
            case 1: points = 100 * (ySpeed - 1); break;
            case 2: points = 50 * (ySpeed - 2); break;
            case 3: points = 100 * ySpeed; break;
            default: points = -2000; break;
        }
    }

    public Rect getRect(){
        return dst;
    }
}
