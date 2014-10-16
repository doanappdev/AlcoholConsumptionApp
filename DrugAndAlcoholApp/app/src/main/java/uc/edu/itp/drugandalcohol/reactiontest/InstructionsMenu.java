package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by wh0-r-u on 16/10/2014.
 */
public class InstructionsMenu extends GameComponent {

    private InstructionsData data;

    public InstructionsMenu(){
        super();
        //Do something below
    }

    @Override
    public boolean condition()
    {
        //Insert condition
        return false;
    }

    @Override
    public void reset(long currentTime){
        //Can also be used as an initializer
    }

    @Override
    public void update(long currentTime, long previousTime){
        //Should not have functions in here!
    }

    @Override
    public void onDraw(Canvas canvas){
        //Draw the menu functions
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //Control the button functions
        return condition();
    }

    private class InstructionsData{

    }
}