package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by AppDev on 14/10/2014.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.GameScore;
import uc.edu.itp.drugandalcohol.view.ReactionTestActivity;


/**
 * Created by AppDev on 12/10/2014.
 */
public class GameView extends SurfaceView
{
    private Bitmap bmp; //bitmap for buttons and sprites
    private SurfaceHolder holder; //used to synchronise events
    private GameLoopThread gameLoopThread; //The loop to control the game
    private GameplayFunction gameplay; //Gameplay function

    //timer variables used
    private long lastClick;
    private long currentTime;
    private long previousTime;

    //condition variables used
    private int eventAction;
    private boolean isClosed;
    private boolean pressed;

    //game score data
    private int score;
    private int hits;
    private int misses;
    private String timeText;
    private String hitTNT;

    //Stores settings from previous activities
    private GameSettings settings;

    //sets up the surface view
    public GameView(Context context, GameSettings settings){
        super(context);

        this.settings = settings;
        pressed = false;

        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        //Insert Error here.
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                init();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                //Do something
                //gameplay.changeSurface(width, height);

            }
        });
    }

    //Initialises the gameplay function and the bitmap image
    private void init(){
        isClosed = false;

        //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alcohol_sprites);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alcohol_sprites_v2);

        gameplay = new GameplayFunction(this, bmp, settings);

        currentTime = System.currentTimeMillis();
        previousTime = currentTime;

        gameplay.reset(currentTime);
    }

    //Has a closing condition so that game exits
    //without any errors
    @Override
    protected void onDraw(Canvas canvas){

        if(isClosed) {
            //previousTime = currentTime;
            gameplay.clean();
            getResults();

            doLose();
        }else{
            canvas.drawColor(Color.WHITE);
            currentTime = System.currentTimeMillis();

            gameplay.update(currentTime, previousTime);
            gameplay.onDraw(canvas);

            isClosed = gameplay.condition();
        }
    }

    //controls the flow of tapping
    @Override
    public boolean onTouchEvent(MotionEvent event){ //300
        eventAction = event.getAction();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                //if the player holds down the screen, nothing will happen
                if (!pressed && System.currentTimeMillis() - lastClick > 100){
                    pressed = true;
                    lastClick = System.currentTimeMillis();
                    synchronized (getHolder()){
                        isClosed = gameplay.onTouchEvent(event);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //allows the player to touch the screen again if they lift
                //their finger off the screen
                pressed = false;
                break;
        }
        return true;
    }

    //Is called from activity function anytime
    public void closeGame(){
        isClosed = true;
    }

    //Requests its parent to finish game
    public void doLose() {
        synchronized (holder) {
            //quit to mainmenu
            ((ReactionTestActivity)super.getContext()).isFinished
                    (score, hits, misses, hitTNT, timeText);
            //((Activity) super.getContext()).finish();
        }
    }

    //saves results from completed game. hitTNT variable
    //is saved as a string for the game over activity to
    //display the results
    private void getResults(){
        score = gameplay.getScore();
        hits = gameplay.getHits();
        misses = gameplay.getMisses();
        hitTNT = gameplay.getHitTNT() ? "YES" : "NO";
        timeText = gameplay.getSpentTimeText();

        GameScore.getInstance().setScore(score);
        GameScore.getInstance().setHits(hits);
        GameScore.getInstance().setMisses(misses);
        GameScore.getInstance().setAnyTntHit(gameplay.getHitTNT());
        GameScore.getInstance().setTimeText(timeText);

        //Testing if GameScore object captures data
        //Log.d("GameView - Score: ", String.valueOf(GameScore.getInstance().getScore()));
        //Log.d("GameView - Time: ", GameScore.getInstance().getTimeText());
    }
}
