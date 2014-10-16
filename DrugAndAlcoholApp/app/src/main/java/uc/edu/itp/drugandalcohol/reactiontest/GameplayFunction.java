package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
public class GameplayFunction extends GameComponent {

    //Fixed times and speed
    private final int secondsToMills = 1000;
    private final int beerSpeed = 2;
    private final int wineSpeed = 1;
    private final int kegSpeed = 2;
    private final int spiritSpeed = 0;

    //Time Formatting Used
    private String timeText;
    private long resetTime;
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
    private ButtonClass button;
    private AlcoholClass currentAlcohol;

    public GameplayFunction(GameView view, Bitmap bmp){
        super();

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

        buttons = new ButtonClass[buttonCount];
        for(i = 0; i < buttonCount; i++) {
            buttons[i] = new ButtonClass(view, i, bmp, 5, 2, 0);
            buttons[i].silouhette = true;
        }
    }

    @Override
    public boolean condition()
    {
        return (misses >= 10 || HitTNT);
    }

    @Override
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
        TNTTimer = 20L;

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
        TNT.destroyTNT();
    }

    @Override
    public void update(long currentTime, long previousTime){
        updateTime(currentTime, previousTime);
        spawnSprites(currentTime);
    }

    @Override
    public void onDraw(Canvas canvas){
        updateSprites(canvas);
        updateText(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        for (i = buttons.length - 1; i >= 0; i--) {
            button = buttons[i];
            if (!button.silouhette && button.isCollision(event.getX(), event.getY())) {
                checkButtonCondition(button.id);
                break;
            }
        }
        if(misses >= 10 || HitTNT){
            clean();
            view.currentScreen = view.GAME_OVER;
        }
        return condition();
    }

    public int getScore(){ return score; }
    public int getHits(){ return hits; }
    public int getMisses(){ return misses; }

    private void updateTime(long currentTime, long previousTime){
        timeCount = currentTime - resetTime;
        if(timeCount >= (15 * secondsToMills)){
            resetTime = currentTime;
            if(beerTimer > 1)beerTimer--;
            if(wineTimer > 1)wineTimer--;
            if(kegTimer > 1)kegTimer--;
            if(spiritTimer > 1)spiritTimer--;
            if(TNTTimer > 1)TNTTimer--;
            gameSpeed++;
        }

        timeCount = currentTime - previousTime;
        seconds = (int)(timeCount/1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        timeText = "Time - " + Integer.toString(minutes) + ":";
        if(seconds < 10) timeText += "0";
        timeText += Integer.toString(seconds);
    }

    private void checkButtonCondition(int id){
        switch(id){
            case 0:
                if(kegs.size() > 0) kegCount = quickTap(kegCount,kegs);
                break;
            case 1:
                if(wines.size() > 0) wineCount = timedTap(wineCount, wines);
                break;
            case 2:
                if(beers.size() > 0) beerCount = quickTap(beerCount, beers);
                break;
            case 3:
                if(spirits.size() > 0) spiritCount = timedTap(spiritCount, spirits);
                break;
            case 4:
                score += TNT.getPoints();
                TNT.destroyTNT();
                button.silouhette = true;
                HitTNT = true;
                break;
            default:
                break;
        }
        if(score < 0) score = 0;
    }

    private int quickTap(int count, Queue<AlcoholClass> queue){
        currentAlcohol = queue.remove();
        if(currentAlcohol.midY > button.getYLimit()){
            score += currentAlcohol.getPoints();
            hits++;
        } else misses++;
        inactives.add(currentAlcohol);
        if(--count < 1) button.silouhette = true;
        return count;
    }

    private int timedTap(int count, Queue<AlcoholClass> queue){
        currentAlcohol = queue.remove();
        if(button.isIntersecting(currentAlcohol.getRect())){
            score += currentAlcohol.getPoints();
            hits++;
        } else misses++;
        inactives.add(currentAlcohol);
        if(--count < 1) button.silouhette = true;
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
        for(i = 0; i < buttonCount; i++) {
            button = buttons[i];
            button.onDraw(canvas);
        }

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
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);

        canvas.drawText("Score - " + Integer.toString(score), 0, 20, paint);
        canvas.drawText("Misses - " + Integer.toString(misses), 130, 20, paint);
        canvas.drawText(timeText, 300, 20, paint);
    }
}
