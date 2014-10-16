package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
public class MainMenu extends GameComponent {

    private GameView view;

    private ButtonClass buttons[];
    private Logo logo;

    private int i;
    private int buttonCount;
    private ButtonClass button;

    private boolean condition;

    public MainMenu(GameView view, Bitmap title, Bitmap buttonSprites){
        super();
        //Do something below
        this.view = view;

        logo = new Logo(view, title, 1, 1);

        buttons = new ButtonClass[]{
            new ButtonClass(view, 0, buttonSprites, 2, 4, 1),
            new ButtonClass(view, 1, buttonSprites, 2, 4, 1),
            new ButtonClass(view, 2, buttonSprites, 2, 4, 1)
        };

        buttonCount = 3;
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
    }

    @Override
    public void update(long currentTime, long previousTime){
        //Should not have functions in here!
    }

    @Override
    public void onDraw(Canvas canvas){
        //Draw the menu functions
        logo.onDraw(canvas);

        for(i = 0; i < buttonCount; i++) {
            button = buttons[i];
            button.onDraw(canvas);
        }
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
            case 0: view.currentScreen = view.GAMEPLAY; break;
            case 1: view.currentScreen = view.GAMEPLAY; break;
            case 2: view.isClosed = true; break;
            default: break;
        }
    }
}
