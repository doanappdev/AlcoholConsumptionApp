package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by wh0-r-u on 15/10/2014.
 */
public class GameplayFunction {

    //Fixed times and speed
    private final int secondsToMills = 1000;

    //Fixed miss conditions
    private final int MAX_MISSES = 5;

    //Width and height
    private int g_width;
    private int g_height;
    private int g_lowest;
    private float aspect;

    //Time Formatting Used
    private String timeText;
    private long resetTime;
    private long spentTime;
    private int seconds;
    private int minutes;

    //Speed variables used
    private int gameSpeed;
    private int randomSpeed;

    //RNG variables used
    private int rngResults;
    private Random random;

    //Timer counts used
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

    //Storages for alcohol sprites
    private List<AlcoholClass> kegs;
    private List<AlcoholClass> wines;
    private List<AlcoholClass> beers;
    private List<AlcoholClass> spirits;
    private Queue<AlcoholClass> inactives;
    private AlcoholClass TNT;

    //Storage for bitmap and View
    private Bitmap bmp;
    private GameView view;
    private GameSettings settings;

    //Storage for buttons
    private ButtonClass buttons[];

    //Paint for colouring
    private Paint paint;

    //Scoring system used
    private int currentScore;
    private int prevScore;
    private int scoreThreshold;
    private int hits;
    private int misses;
    private boolean HitTNT;

    //Temporary variables for faster performance
    private int i;
    private int tempInt;
    private int currentInt;
    private AlcoholClass currentAlcohol;

    //NOTE: The commented overrides appear because it used to have a
    //game component system where the surface views show the main menu,
    //game over, instructions, high score, and settings

    //Sets up the game every time it gets created
    public GameplayFunction(GameView view, Bitmap bmp, GameSettings settings){
        //super();

        this.bmp = bmp;
        this.view = view;
        this.settings = settings;

        buttonCount = 5;

        paint = new Paint();
        beers = new LinkedList<AlcoholClass>();
        wines = new LinkedList<AlcoholClass>();
        kegs = new LinkedList<AlcoholClass>();
        spirits = new LinkedList<AlcoholClass>();
        inactives = new LinkedList<AlcoholClass>();

        TNT = new AlcoholClass(view, 4, bmp, 3, 2);

        float xResult, yResult;
        int width = (bmp.getWidth()/3);
        g_width = view.getWidth();
        g_height = view.getHeight();
        g_lowest = (g_width > g_height) ? g_height : g_width;

        buttons = new ButtonClass[buttonCount];
        for(i = 0; i < buttonCount; i++) {
            buttons[i] = new ButtonClass(view, i, bmp, 3, 2);

            //x = 0.8 of game width * id * 1/(number of buttons-1)

            //The formula of x is calculated by having 80% of game
            //width times the id number of a button divided by 1 less
            //the number of buttons in total, plus 10% of game width,
            //minus the scaled width of the button divided by 2.
            xResult = (g_width*i/5) + (g_width/10) - width;
            //Log.d("Gameplay Function: X - ", String.valueOf(xResult));
            yResult = g_height*0.85f;

            buttons[i].setPosX(xResult);
            buttons[i].setPosY(yResult);
        }
    }

    //@Override
    //returns the conditions to end game
    public boolean condition()
    {
        return (misses >= MAX_MISSES || HitTNT);
    }

    //@Override
    //see NOTE just above the class constructor
    public void reset(long currentTime){
        currentScore = 0;
        prevScore = 0;
        //scoreThreshold = 500;
        scoreThreshold = 750;
        //scoreThreshold = 1000;
        hits = 0;
        misses = 0;
        HitTNT = false;

        timeCount = 0;
        gameSpeed = 2;
        randomSpeed = 0;

        TNT.reset(0, gameSpeed, 4);

        beerTimer = 5000L;
        wineTimer = 7500L;
        kegTimer = 6000L;
        spiritTimer = 9000L;
        TNTTimer = 12000L;

        resetTime = currentTime;
        beerTimerCount = currentTime;
        wineTimerCount = currentTime;
        kegTimerCount = currentTime;
        spiritTimerCount = currentTime;
        TNTTimerCount = currentTime;
    }

    //removes all objects before closing surface view
    public void clean()
    {
        //for(i = 0; i < buttonCount; i++) buttons[i].silouhette = true;

        while(beers.size() > 0) beers.remove(0);
        while(kegs.size() > 0) kegs.remove(0);
        while(wines.size() > 0) wines.remove(0);
        while(spirits.size() > 0) spirits.remove(0);
        while(inactives.size() > 0) inactives.remove();

        //TNT.destroyTNT();
        TNT = null;
    }

    //@Override
    //updates time and sprite functions
    public void update(long currentTime, long previousTime){
        updateTime(currentTime, previousTime);
        spawnSprites(currentTime);
    }

    //@Override
    //renders sprites, buttons, and UI HUD
    public void onDraw(Canvas canvas){
        updateSprites(canvas);
        updateText(canvas);
    }

    //@Override
    //returns the condition for touch event
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

    //returns game score data
    public int getScore(){ return currentScore; }
    public int getHits(){ return hits; }
    public int getMisses(){ return misses; }
    public long getSpentTime(){ return spentTime; }
    public String getSpentTimeText(){
        seconds = (int)(spentTime/1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        timeText = Integer.toString(minutes) + ":";
        if(seconds < 10) timeText += "0";
        timeText += Integer.toString(seconds);
        return timeText;
    }
    public boolean getHitTNT(){ return HitTNT; }

    //unused function
    public void changeSurface(int width, int height)
    {
        g_width = width;
        g_height = height;
        g_lowest = (g_width > g_height) ? g_height : g_width;
    }

    //updates the time and game speed
    private void updateTime(long currentTime, long previousTime){
        timeCount = currentTime - resetTime;
        if(settings.getSpeedByTimer()) {
            if(timeCount >= 20 * secondsToMills) {
                resetTime = currentTime;
                increaseGameSpeed(currentTime);
            }
        }else{
            if((currentScore - prevScore) >= scoreThreshold)
            {
                prevScore = currentScore;
                //scoreThreshold += 100;
                scoreThreshold += 150;
                //scoreThreshold += 200;
                increaseGameSpeed(currentTime);
            }
        }

        spentTime = currentTime - previousTime;
        seconds = (int)(spentTime/1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        timeText = "Time - " + Integer.toString(minutes) + ":";
        if(seconds < 10) timeText += "0";
        timeText += Integer.toString(seconds);
    }

    //increases game speed and reduces spawn timer
    private void increaseGameSpeed(long currentTime){
        if(beerTimer > 500L) beerTimer -= 100L;
        if(wineTimer > 500L) wineTimer -= 100L;
        if(kegTimer > 500L) kegTimer -= 100L;
        if(spiritTimer > 500L) spiritTimer -= 100L;
        if(TNTTimer > 500L) TNTTimer -= 500L;
        gameSpeed++;
    }

    //checks the TNT and the entire collection of spawned
    //sprites
    private void checkButtonCondition(int id){
        switch(id){
            case 0:
                if(beers.size() > 0)
                    //beerCount = quickTap(beerCount, id, beers);
                    timedTap(id, beers);
                break;
            case 1: if(wines.size() > 0) timedTap(id, wines); break;
            case 2:
                if(kegs.size() > 0)
                    //quickTap(id, kegs);
                    timedTap(id, kegs);
                break;
            case 3: if(spirits.size() > 0) timedTap(id, spirits); break;
            case 4:
                currentScore += TNT.getPoints();
                TNT.destroyTNT();
                buttons[id].silouhette = true;
                HitTNT = true;
                break;
            default:
                break;
        }
        if(currentScore < 0) currentScore = 0;
    }

    //This method is for an extra mechanic you could
    //probably use in the future
    private void quickTap(int id, List<AlcoholClass> list){
        getClosest(list);
        if(currentAlcohol.midY < buttons[id].getYLimit()){
            currentScore += currentAlcohol.getPoints();
            inactives.add(currentAlcohol);
            list.remove(currentInt);
            hits++;
            if(list.size() < 1) buttons[id].silouhette = true;
        } else misses++;
    }

    //Default way of scoring
    private void timedTap(int id, List<AlcoholClass> list){
        getClosest(list);
        if(buttons[id].isIntersecting(currentAlcohol.getRect())){
            currentScore += currentAlcohol.getPoints();
            inactives.add(currentAlcohol);
            list.remove(currentInt);
            hits++;
            if(list.size() < 1) buttons[id].silouhette = true;
        }else misses++;
    }

    //returns object closest to the pressed button
    private void getClosest(List<AlcoholClass> list)
    {
        currentAlcohol = list.get(0);
        for(int j = 1; j < list.size(); j++){
            if(list.get(j).midY > currentAlcohol.midY){
                currentAlcohol = list.get(j);
                currentInt = j;
            }
        }
    }

    //checks if new sprites are made
    private void spawnSprites(long currentTime){
        beerTimerCount = spawnSprite(currentTime, beerTimerCount, beerTimer, gameSpeed, 0, beers);
        wineTimerCount = spawnSprite(currentTime, wineTimerCount, wineTimer, gameSpeed, 1, wines);
        kegTimerCount = spawnSprite(currentTime, kegTimerCount, kegTimer, gameSpeed, 2, kegs);
        spiritTimerCount = spawnSprite(currentTime, spiritTimerCount, spiritTimer,
                gameSpeed, 3, spirits);

        timeCount = currentTime - TNTTimerCount;
        if(timeCount >= TNTTimer){
            TNTTimerCount = currentTime;
            TNT.ResetTNT();
            if (buttons[4].silouhette) buttons[4].silouhette = false;
        }
    }

    //creates a particular sprite
    private long spawnSprite(long currentTime, long count, long timer, int speed,
                            int id, List<AlcoholClass> list){
        timeCount = currentTime - count;
        if (timeCount >= timer){
            if(settings.getRandomiseSpeed()) genRandomSpeed();
            currentAlcohol = Pop(id);
            currentAlcohol.reset(0, speed+randomSpeed, id);
            list.add(currentAlcohol);
            if(buttons[id].silouhette) buttons[id].silouhette = false;
            return currentTime;
        }else return count;
    }

    //recycling system to reduce wasted memory storage
    private AlcoholClass Pop(int id){
        return (inactives.size() > 0) ?
                inactives.remove() :
                new AlcoholClass(view,id,bmp,3,2);
    }

    //places finished sprites here
    private void Push(AlcoholClass inactive){
        inactives.add(inactive);
    }

    //move and draw all sprites
    private void updateSprites(Canvas canvas){
        for(i = 0; i < buttonCount; i++)
            buttons[i].onDraw(canvas);

        updateSprite(canvas, 0, beers);
        updateSprite(canvas, 1, wines);
        updateSprite(canvas, 2, kegs);
        updateSprite(canvas, 3, spirits);

        TNT.onDraw(canvas);
        //checks if button needs to be darken.
        if(!TNT.active && !buttons[4].silouhette) buttons[4].silouhette = true;
    }

    //move and draw a sprite
    private void updateSprite(Canvas canvas, int id, List<AlcoholClass> list){
        for(i = 0; i < list.size(); i++) {
            list.get(i).onDraw(canvas);
            //sprite is removed if player fails to time their taps
            if(!list.get(i).active){
                currentAlcohol = list.get(i);
                Push(currentAlcohol);
                list.remove(i);
                i--;
                misses++;
                if(list.size() < 1 && !buttons[id].silouhette)
                    buttons[id].silouhette = true;
            }
        }
    }

    //updates current score, misses, and time
    private void updateText(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 128, 0));
        canvas.drawRect(0, 0, g_width, g_height/5, paint);

        paint.setColor(Color.BLUE);
        paint.setTextSize(20*g_lowest/320);

        //currentScore is too long of a text compared to Score
        //At 600 pixels wide
        canvas.drawText("Score - " + Integer.toString(currentScore), 0, g_height/20, paint);
        canvas.drawText("Misses - " + Integer.toString(misses), g_width*9/20, g_height/20, paint);
        canvas.drawText(timeText, 0, g_height*3/20, paint);
    }

    //makes sure moving sprites remaing "exciting" for the player
    private void genRandomSpeed(){
        //rngResults = random.nextInt(10)
        switch(rngResults = (int)(Math.random()*10)){
            case 1: randomSpeed = 1; break;
            case 2:
                randomSpeed = 1;
                //if(scoreThreshold >= 700) randomSpeed++;
                if(scoreThreshold >= 1050) randomSpeed++;
                //if(scoreThreshold >= 1400) randomSpeed++;
                break;
            case 3:
                randomSpeed = 2;
                //if(scoreThreshold >= 700) randomSpeed++;
                if(scoreThreshold >= 1050) randomSpeed++;
                //if(scoreThreshold >= 1400) randomSpeed++;
                break;
            case 4:
                randomSpeed = 2;
                //if(scoreThreshold >= 700) randomSpeed++;
                if(scoreThreshold >= 1050) randomSpeed++;
                //if(scoreThreshold >= 1400) randomSpeed++;
                //if(scoreThreshold >= 1100) randomSpeed++;
                if(scoreThreshold >= 1650) randomSpeed++;
                //if(scoreThreshold >= 2200) randomSpeed++;
                break;
            case 5:
                randomSpeed = 3;
                //if(scoreThreshold >= 700) randomSpeed++;
                if(scoreThreshold >= 1050) randomSpeed++;
                //if(scoreThreshold >= 1400) randomSpeed++;
                //if(scoreThreshold >= 1100) randomSpeed++;
                if(scoreThreshold >= 1650) randomSpeed++;
                //if(scoreThreshold >= 2200) randomSpeed++;
                break;
            default:
                randomSpeed = 0;
                break;
        }
        //Log.d("RNG - ", String.valueOf(rngResults));
    }
}
