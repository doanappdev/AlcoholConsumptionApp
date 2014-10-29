package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class AlcoholClass extends Sprite {

    //these variables are different from buttons
    private float endY;
    private int xSpeed;
    private int ySpeed;
    private int points;
    public boolean active;

    //used to be able to spawn with different colors
    private int rng;

    //Sets values if this class is created for the first time
    public AlcoholClass(GameView gameView, int id, Bitmap bmp, final int columns, final int rows) {
        super(gameView, id, bmp, columns, rows);

        active = false;
        endY = gameView.getHeight()*0.85f;
    }

    //each time this class is created or reused, values are
    //reset in this fashion.
    public void reset(int xSpeed, int ySpeed, int id){
        if(this.id != id){
            this.id = id;
            srcX = id%BMP_COLUMNS * width;
            srcY = id/BMP_COLUMNS * height;
            src.set(srcX , srcY, srcX + width, srcY + height);
        }

        //rng = (int)(Math.random()*8);

        //These colours are different from their
        //button counterparts. Is that what you want?
        switch(this.id){
            case 0:setColor(255,255,128,255);break;
            case 1:setColor(255,255,128,0);break;
            case 2:setColor(255,0,160,128);break;
            case 3:setColor(255,100,100,0);break;
            default:setColor(255,0,0,0);break;
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

    //Resets the TNT only, as TNT is not in a list
    public void ResetTNT(){
        y = 0;
        //y = gameView.getHeight()/20;
        active = true;
    }

    //updates the sprite
    //@Override
    protected void update() {
        y += ySpeed;
        midY += ySpeed;
        if (y > endY) active = false;
    }

    //updates the sprite.
    //for TNT only, as it is not in a list.
    private void TNTUpdate() {
        if(active){
            y += ySpeed;
            if (y >= endY) {
                y = gameView.getHeight()+100;
                active = false;
            }
        }
    }

    //draws the alcohol
    @Override
    public void onDraw(Canvas canvas) {
        //checks if alcohol is a TNT
        if(id < 4) update();
        else TNTUpdate();
        //sets size and position of alcohol
        dst.set(x, y, x + width * 2, y + height * 2);
        if(id > 3) canvas.drawBitmap(bmp, src, dst, null);
        else canvas.drawBitmap(bmp, src, dst, paint);
    }

    //destroys TNT only by making it inactive,
    //TNT only because it is not in a list
    public void destroyTNT(){
        y = endY;
        active = false;
    }

    //shows the point values
    public int getPoints()
    {
        return points;
    }
    //adjusts the point values
    private void setPoints(){
        //TNT has negative scores, because it
        //should not be hit AT ALL
        switch(id){
            case 4: points = -2000; break;
            default: points = 50 * (ySpeed - 1); break;
        }
    }

    //returns its hitbox
    public RectF getRect(){
        return dst;
    }
}
