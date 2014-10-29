package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by AppDev on 14/10/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

//This loop thread makes sure it maintains running at
//set FPS. Also checks if it continues running or not
public class GameLoopThread extends Thread
{
    static final long FPS = 60;
    private GameView view;
    private boolean running = false;

    public GameLoopThread(GameView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        // limit drawing to 10 FPS, that is 100ms
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();

            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    //ignore this warning
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }

            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}