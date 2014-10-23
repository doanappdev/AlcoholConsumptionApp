package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
public class GameplayFunction {

    //Fixed times and speed
    private final int MAX_MISSES = 5;

    private final int secondsToMills = 1000;
    private final int beerSpeed = 2;
    private final int wineSpeed = 1;
    private final int kegSpeed = 2;
    private final int spiritSpeed = 1;

    private int g_width;
    private int g_height;
    private float aspect;

    //Time Formatting Used
    private String timeText;
    private long resetTime;
    private long spentTime;
    private int seconds;
    private int minutes;

    //Timer counts used
    private int gameSpeed;
    private long timeCount;
    private long beerTimerCount;
    private long wineTimerCount;
    private long kegTimerCount;
    private long spiritTimerCount;
    private long TNTTimerCount;

    //Timer conditions used
    private long beerTimer;
    private long wineTimer;
    private long kegTimer;
    private long spiritTimer;
    private long TNTTimer;

    //Counters used for all buttons
    private int buttonCount;

    //Counters used for temporary sprites
    private int beerCount;
    private int wineCount;
    private int kegCount;
    private int spiritCount;

    //Storages for alcohol sprites
    private Queue<AlcoholClass> kegs;
    private Queue<AlcoholClass> wines;
    private Queue<AlcoholClass> beers;
    private Queue<AlcoholClass> spirits;
    private Queue<AlcoholClass> inactives;
    private AlcoholClass TNT;

    //Storage for bitmap and View
    private Bitmap bmp;
    private GameView view;

    //Storage for buttons
    private ButtonClass buttons[];

    //Paint for colouring
    private Paint paint;

    //Scoring system used
    private int score;
    private int hits;
    private int misses;
    private boolean HitTNT;

    //Temporary variables for faster performance
    private int i;
    private int tempInt;
    private AlcoholClass currentAlcohol;

    public GameplayFunction(GameView view, Bitmap bmp){
        //super();

        this.bmp = bmp;
        this.view = view;
        buttonCount = 5;

        paint = new Paint();
        beers = new LinkedList<AlcoholClass>();
        wines = new LinkedList<AlcoholClass>();
        kegs = new LinkedList<AlcoholClass>();
        spirits = new LinkedList<AlcoholClass>();
        inactives = new LinkedList<AlcoholClass>();

        TNT = new AlcoholClass(view, 4, bmp, 5, 2);

        float xResult, yResult;
        int width = (bmp.getWidth()/5);
        g_width = view.getWidth();
        g_height = view.getHeight();

        buttons = new ButtonClass[buttonCount];
        for(i = 0; i < buttonCount; i++) {
            buttons[i] = new ButtonClass(view, i, bmp, 5, 2);
            buttons[i].silouhette = true;

            //x = 0.8 of game width * id * 1/(number of buttons-1)

            //The formula of x is calculated by having 80% of game
            //width times the id number of a button divided by 1 less
            //the number of buttons in total, plus 10% of game width,
            //minus the scaled width of the button divided by 2.
            xResult = (g_width*i/5) + (g_width/10) - width;
            Log.d("Gameplay Function: X - ", String.valueOf(xResult));
            yResult = g_height - (g_height*0.15f);

            buttons[i].setPosX(xResult);
            buttons[i].setPosY(yResult);
        }
    }

    //@Override
    public boolean condition()
    {
        return (misses >= MAX_MISSES || HitTNT);
    }

    //@Override
    public void reset(long currentTime){
        score = 0;
        hits = 0;
        misses = 0;
        HitTNT = false;

        timeCount = 0;
        gameSpeed = 1;

        beerCount = 0;
        wineCount = 0;
        kegCount = 0;
        spiritCount = 0;

        beerTimer = 5L;
        wineTimer = 8L;
        kegTimer = 6L;
        spiritTimer = 10L;
        TNTTimer = 13L;

        resetTime = currentTime;
        beerTimerCount = currentTime;
        wineTimerCount = currentTime;
        kegTimerCount = currentTime;
        spiritTimerCount = currentTime;
        TNTTimerCount = currentTime;
    }

    public void clean()
    {
        for(i = 0; i < buttonCount; i++) buttons[i].silouhette = true;
        for(i = 0; i < beerCount; i++){
            currentAlcohol = beers.remove();
            Push(currentAlcohol);
        }
        for(i = 0; i < kegCount; i++){
            currentAlcohol = kegs.remove();
            Push(currentAlcohol);
        }
        for(i = 0; i < wineCount; i++){
            currentAlcohol = wines.remove();
            Push(currentAlcohol);
        }
        for(i = 0; i < spiritCount; i++){
            currentAlcohol = spirits.remove();
            Push(currentAlcohol);
        }

        beerCount = 0;
        wineCount = 0;
        kegCount = 0;
        spiritCount = 0;

        TNT.destroyTNT();
    }

    //@Override
    public void update(long currentTime, long previousTime){
        updateTime(currentTime, previousTime);
        spawnSprites(currentTime);
    }

    //@Override
    public void onDraw(Canvas canvas){
        updateSprites(canvas);
        updateText(canvas);
    }

    //@Override
    public boolean onTouchEvent(MotionEvent event){
        for (i = buttons.length - 1; i >= 0; i--) {
            if (!buttons[i].silouhette && buttons[i].isCollision(event.getX(), event.getY())) {
                checkButtonCondition(buttons[i].id);
                break;
            }
        }
        /*if(misses >= MAX_MISSES || HitTNT){
            clean();
            //view.currentScreen = view.GAME_OVER;
        }*/
        return condition();
    }

    public int getScore(){ return score; }
    public int getHits(){ return hits; }
    public int getMisses(){ return misses; }
    public long getSpentTime(){ return spentTime; }
    public boolean getHitTNT(){ return HitTNT; }

    private void updateTime(long currentTime, long previousTime)
    {
        timeCount = currentTime - resetTime;
        if(timeCount >= (15 * secondsToMills))
        {
            resetTime = currentTime;
            if(beerTimer > 1)beerTimer--;
            if(wineTimer > 1)wineTimer--;
            if(kegTimer > 1)kegTimer--;
            if(spiritTimer > 1)spiritTimer--;
            if(TNTTimer > 1)TNTTimer--;
            gameSpeed++;
        }

        spentTime = currentTime - previousTime;
        seconds = (int)(spentTime/1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        timeText = "Time - " + Integer.toString(minutes) + ":";
        if(seconds < 10) timeText += "0";
        timeText += Integer.toString(seconds);
    }

    private void checkButtonCondition(int id){
        switch(id){
            case 0:
                if(kegs.size() > 0) kegCount = quickTap(kegCount, id, kegs);
                break;
            case 1:
                if(wines.size() > 0) wineCount = timedTap(wineCount, id, wines);
                break;
            case 2:
                if(beers.size() > 0) beerCount = quickTap(beerCount, id, beers);
                break;
            case 3:
                if(spirits.size() > 0) spiritCount = timedTap(spiritCount, id, spirits);
                break;
            case 4:
                score += TNT.getPoints();
                TNT.destroyTNT();
                buttons[id].silouhette = true;
                HitTNT = true;
                break;
            default:
                break;
        }
        if(score < 0) score = 0;
    }

    private int quickTap(int count, int id, Queue<AlcoholClass> queue){
        currentAlcohol = queue.remove();
        if(currentAlcohol.midY < buttons[id].getYLimit()){
            score += currentAlcohol.getPoints();
            hits++;
        } else misses++;
        inactives.add(currentAlcohol);
        if(--count < 1) buttons[id].silouhette = true;
        return count;
    }

    private int timedTap(int count, int id, Queue<AlcoholClass> queue){
        currentAlcohol = queue.remove();
        if(buttons[id].isIntersecting(currentAlcohol.getRect())){
            score += currentAlcohol.getPoints();
            hits++;
        } else misses++;
        inactives.add(currentAlcohol);
        if(--count < 1) buttons[id].silouhette = true;
        return count;
    }

    private void spawnSprites(long currentTime){
        kegCount += spawnSprite(currentTime, kegTimerCount, kegTimer, kegSpeed+
                gameSpeed, 0, kegs);
        kegTimerCount = timeCount;

        wineCount += spawnSprite(currentTime, wineTimerCount, wineTimer, wineSpeed+
                gameSpeed, 1, wines);
        wineTimerCount = timeCount;

        beerCount += spawnSprite(currentTime, beerTimerCount, beerTimer, beerSpeed+
                gameSpeed, 2, beers);
        beerTimerCount = timeCount;

        spiritCount += spawnSprite(currentTime, spiritTimerCount, spiritTimer,
                spiritSpeed+gameSpeed, 3, spirits);
        spiritTimerCount = timeCount;

        timeCount = currentTime - TNTTimerCount;
        if(timeCount >= (long)(TNTTimer*secondsToMills)){
            TNTTimerCount = currentTime;
            TNT.ResetTNT();
            if (buttons[4].silouhette) buttons[4].silouhette = false;
        }
    }

    private int spawnSprite(long currentTime, long count, long timer, int speed,
                            int id, Queue<AlcoholClass> queue){
        timeCount = currentTime - count;
        if (timeCount >= timer*secondsToMills){
            timeCount = currentTime;
            currentAlcohol = Pop(id);
            currentAlcohol.reset(0, speed, id);
            queue.add(currentAlcohol);
            if(buttons[id].silouhette) buttons[id].silouhette = false;
            return 1;
        }else{
            timeCount = count;
            return 0;
        }
    }

    private AlcoholClass Pop(int id){
        if(inactives.size() > 0) return inactives.remove();
        else return new AlcoholClass(view, id, bmp, 5, 2);
    }

    private void Push(AlcoholClass inactive)
    {
        inactives.add(inactive);
    }

    private void updateSprites(Canvas canvas){
        for(i = 0; i < buttonCount; i++)
            buttons[i].onDraw(canvas);

        kegCount = updateSprite(canvas, kegCount, 0, kegs);
        wineCount = updateSprite(canvas, wineCount, 1, wines);
        beerCount = updateSprite(canvas, beerCount, 2, beers);
        spiritCount = updateSprite(canvas, spiritCount, 3, spirits);

        TNT.TNTUpdate();
        TNT.onDraw(canvas);
        if(!TNT.active && !buttons[4].silouhette) buttons[4].silouhette = true;
    }

    private int updateSprite(Canvas canvas, int count, int id, Queue<AlcoholClass> queue){
        tempInt = count;
        for(i = 0; i < count; i++) {
            currentAlcohol = queue.remove();
            currentAlcohol.onDraw(canvas);
            if(currentAlcohol.active) queue.add(currentAlcohol);
            else{
                Push(currentAlcohol);
                tempInt--;
                misses++;
                if(tempInt < 1 && !buttons[id].silouhette) buttons[id].silouhette = true;
            }
        }
        return tempInt;
    }

    private void updateText(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 128, 0));
        canvas.drawRect(0, 0, g_width, g_height/5, paint);

        paint.setColor(Color.BLUE);
        paint.setTextSize(20*g_width/320);

        canvas.drawText("Score - " + Integer.toString(score), 0, g_height/20, paint);
        canvas.drawText("Misses - " + Integer.toString(misses), g_width*9/20, g_height/20, paint);
        canvas.drawText(timeText, 0, g_height*3/20, paint);
    }
}
