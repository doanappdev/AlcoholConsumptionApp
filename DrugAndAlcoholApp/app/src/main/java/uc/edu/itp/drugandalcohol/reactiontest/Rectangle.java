package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by AppDev on 14/10/2014.
 */
public class Rectangle
{
    private GameView gameView;
    private RectF rect1;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float blockWidth;
    private float blockHeight;
    private float posX;
    private float posY;
    private int color;
    private int ySpeed;

    public Rectangle(GameView view, float posX, float posY, int color)
    {
        this.gameView = view;
        this.posX = posX;
        this.posY = posY;
        this.color = color;

        blockWidth =  gameView.getWidth() / 3;
        blockHeight =  gameView.getHeight() / 3;

        ySpeed = 5;
    }


    private void update(int speed)
    {
        if(posY > gameView.getHeight())
        {
            posY = -blockHeight;
        }

        posY += speed;
    }

    public void onDraw(Canvas canvas, int ySpeed)
    {
        update(ySpeed);

        // Rect(left, top, right, bottom)
        rect1 = new RectF(posX, posY, posX + blockWidth, posY + blockHeight);
        paint.setColor(color);
        canvas.drawRect(rect1, paint);




    }

    public boolean isClickedOn(float x2, float y2)
    {
        return x2 > posX && x2 < posX + blockWidth && y2 > posY && y2 < posY + blockHeight;
    }
}
