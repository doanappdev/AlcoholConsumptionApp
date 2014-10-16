package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by wh0-r-u on 16/10/2014.
 */
public class GameOverMenu extends GameComponent {

    private GameView view;
    private GameplayFunction scoreKeeper;

    private ButtonClass buttons[];
    private Paint paint;

    private int i;
    private int buttonCount;
    private ButtonClass button;

    private boolean condition;

    private int score;
    private int hits;
    private int misses;

    public GameOverMenu(GameView view, Bitmap buttonSprites, GameplayFunction scoreKeeper){
        super();
        //Do something below
        this.view = view;
        this.scoreKeeper = scoreKeeper;

        paint = new Paint();

        score = 0;
        hits = 0;
        misses = 0;

        buttons = new ButtonClass[]{
                new ButtonClass(view, 6, buttonSprites, 2, 4, 2),
                new ButtonClass(view, 5, buttonSprites, 2, 4, 2)
        };

        buttonCount = 2;
    }

    @Override
    public boolean condition()
    {
        //Insert condition
        return condition;
    }

    @Override
    public void reset(long currentTime){
        //Can also be used as an initializer
        condition = false;
        score = scoreKeeper.getScore();
        hits = scoreKeeper.getHits();
        misses = scoreKeeper.getMisses();
    }

    @Override
    public void update(long currentTime, long previousTime){
        //Should not have functions in here!
    }

    @Override
    public void onDraw(Canvas canvas){
        //Draw the menu functions
        for(i = 0; i < buttonCount; i++) {
            button = buttons[i];
            button.onDraw(canvas);
        }
        updateText(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //Control the button functions
        for (i = buttons.length - 1; i >= 0; i--) {
            button = buttons[i];
            if (!button.silouhette && button.isCollision(event.getX(), event.getY())) {
                checkButtonCondition(button.id);
                break;
            }
        }
        return condition;
    }

    private void checkButtonCondition(int id){
        condition = true;
        switch(id){
            case 5: view.currentScreen = view.MAIN_MENU; break;
            case 6: view.isClosed = true; break;
            default: break;
        }
    }

    private void updateText(Canvas canvas){
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);

        canvas.drawText("Score - " + Integer.toString(score), 120, 20, paint);
        canvas.drawText("Hits - " + Integer.toString(hits), 130, 45, paint);
        canvas.drawText("Misses - " + Integer.toString(misses), 130, 70, paint);
    }
}
