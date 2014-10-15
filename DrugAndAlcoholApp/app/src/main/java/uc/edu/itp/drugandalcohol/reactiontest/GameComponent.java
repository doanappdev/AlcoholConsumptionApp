package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
abstract class GameComponent {

    public GameComponent(){
        //Do stuff
    }

    abstract void reset(long currentTime);
    abstract void update(long currentTime, long previousTime);
    abstract void onDraw(Canvas canvas);
    abstract void onTouchEvent(MotionEvent event);
}
