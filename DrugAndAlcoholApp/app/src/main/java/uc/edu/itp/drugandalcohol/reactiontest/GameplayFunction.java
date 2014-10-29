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
            //Checks if user input collides with any button.
            //i is the current button id.
            if (!buttons[i].silouhette && buttons[i].isCollision(event.getX(), event.getY())) {
                //this is triggered when button does not have a silouhette and
                //finger hits that button
                checkButtonCondition(buttons[i].id);
                break;
            }
        }
        return condition();
    }

    //returns score
    public int getScore(){ return currentScore; }
    //returns hits
    public int getHits(){ return hits; }
    //returns misses
    public int getMisses(){ return misses; }
    //returns tnt hit
    public boolean getHitTNT(){ return HitTNT; }
    //returns long number of spentTime
    public long getSpentTime(){ return spentTime; }
    //returns string of spent time
    public String getSpentTimeText(){
        seconds = (int)(spentTime/1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        timeText = Integer.toString(minutes) + ":";
        if(seconds < 10) timeText += "0";
        timeText += Integer.toString(seconds);
        return timeText;
    }

    //unused function - belongs to change surfaceView
    public void changeSurface(int width, int height)
    {
        g_width = width;
        g_height = height;
        g_lowest = (g_width > g_height) ? g_height : g_width;
    }

    //updates the time and game speed
    private void updateTime(long currentTime, long previousTime){
        timeCount = currentTime - resetTime;
        //checks setting condition
        if(settings.getSpeedByTimer()) {
            //if difference of current time and previous time
            //exceeds that threshold, increase game speed
            if(timeCount >= 20 * secondsToMills) {
                resetTime = currentTime;
                increaseGameSpeed(currentTime);
            }
        }else{
            //if difference of current score and previous score
            //exceeds that threshold, increase game speed
            if((currentScore - prevScore) >= scoreThreshold)
            {
                prevScore = currentScore;
                //scoreThreshold += 100;
                scoreThreshold += 150;
                //scoreThreshold += 200;
                increaseGameSpeed(currentTime);
            }
        }

        //sets the time spent as string
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
                //checks for beer list
                if(beers.size() > 0)
                    //beerCount = quickTap(beerCount, id, beers);
                    timedTap(id, beers);
                break;
            //checks for wine list
            case 1: if(wines.size() > 0) timedTap(id, wines); break;
            //checks for kegs list
            case 2:
                if(kegs.size() > 0)
                    //quickTap(id, kegs);
                    timedTap(id, kegs);
                break;
            //checks for spirits list
            case 3: if(spirits.size() > 0) timedTap(id, spirits); break;
            //checks for active TNT
            case 4:
                currentScore += TNT.getPoints();
                TNT.destroyTNT();
                buttons[id].silouhette = true;
                HitTNT = true;
                break;
            default:
                break;
        }
        //makes sure score is not a negative number
        if(currentScore < 0) currentScore = 0;
    }

    //This method is for an extra mechanic you could
    //probably use in the future
    private void quickTap(int id, List<AlcoholClass> list){
        getClosest(list);
        //checks if current alcohol is not touching the button
        if(list.get(currentInt).midY < buttons[id].getYLimit()){
            //calculate score
            currentScore += list.get(currentInt).getPoints();
            //removes alcohol from list
            inactives.add(list.get(currentInt));
            list.remove(currentInt);
            //increments hit counter
            hits++;
            //button is black if no alcohol is on the list
            if(list.size() < 1) buttons[id].silouhette = true;
        } else misses++;
    }

    //Default way of scoring
    private void timedTap(int id, List<AlcoholClass> list){
        getClosest(list);
        //checks if current alcohol touches the button
        if(buttons[id].isIntersecting(list.get(currentInt).getRect())){
            //calculate score
            currentScore += list.get(currentInt).getPoints();
            //removes alcohol from list
            inactives.add(list.get(currentInt));
            list.remove(currentInt);
            //increments hit counter
            hits++;
            //button is black if no alcohol is on the list
            if(list.size() < 1) buttons[id].silouhette = true;
        }else misses++;
    }

    //returns object closest to the pressed button
    private void getClosest(List<AlcoholClass> list)
    {
        currentInt = 0;
        for(int j = 1; j < list.size(); j++){
            //condition to check closest alcohol
            if(list.get(j).midY > list.get(currentInt).midY)
                currentInt = j;
        }
    }

    //checks if new sprites are made
    private void spawnSprites(long currentTime){
        //timer count for each specific list gets calculated
        beerTimerCount = spawnSprite(currentTime, beerTimerCount, beerTimer, gameSpeed, 0, beers);
        wineTimerCount = spawnSprite(currentTime, wineTimerCount, wineTimer, gameSpeed, 1, wines);
        kegTimerCount = spawnSprite(currentTime, kegTimerCount, kegTimer, gameSpeed, 2, kegs);
        spiritTimerCount = spawnSprite(currentTime, spiritTimerCount, spiritTimer,
                gameSpeed, 3, spirits);

        //calculates time count for TNT
        timeCount = currentTime - TNTTimerCount;
        //Reset currently active TNT to top of the screen
        //if time count exceeds timer threshold
        if(timeCount >= TNTTimer){
            TNTTimerCount = currentTime;
            TNT.ResetTNT();
            if (buttons[4].silouhette) buttons[4].silouhette = false;
        }
    }

    //creates a particular sprite
    private long spawnSprite(long currentTime, long count, long timer, int speed,
                            int id, List<AlcoholClass> list){
        //calculates time count for alcohol
        timeCount = currentTime - count;
        //Reset currently active alcohol to top of the screen
        //if time count exceeds timer threshold
        if (timeCount >= timer){
            //if game settings have random speed, randomise speed
            //of current alcohol
            if(settings.getRandomiseSpeed()) genRandomSpeed();
            //reuses alcohol if any inactive ones are there
            //and add them to the list
            list.add(Pop(id));
            list.get(list.size()-1).reset(0, speed+randomSpeed, id);
            if(buttons[id].silouhette) buttons[id].silouhette = false;
            return currentTime;
        }else return count;
    }

    //recycling system to reduce wasted memory storage
    private AlcoholClass Pop(int id){
        //if there are any inactive sprites, reuse them.
        //else, create new alcohol
        return (inactives.size() > 0) ?
                inactives.remove() :
                new AlcoholClass(view,id,bmp,3,2);
    }

    //places finished sprites here and make them inactive
    private void Push(AlcoholClass inactive){
        inactives.add(inactive);
    }

    //move and draw all sprites
    private void updateSprites(Canvas canvas){
        //each button is drawn here
        for(i = 0; i < buttonCount; i++)
            buttons[i].onDraw(canvas);

        //updates and draws all alcohol sprites here
        updateSprite(canvas, 0, beers);
        updateSprite(canvas, 1, wines);
        updateSprite(canvas, 2, kegs);
        updateSprite(canvas, 3, spirits);

        //draws and updates TNT
        TNT.onDraw(canvas);
        //checks if button needs to be darken.
        if(!TNT.active && !buttons[4].silouhette) buttons[4].silouhette = true;
    }

    //move and draw a sprite
    private void updateSprite(Canvas canvas, int id, List<AlcoholClass> list){
        for(i = 0; i < list.size(); i++) {
            //draws current alcohol
            list.get(i).onDraw(canvas);
            //sprite is removed if player fails to time their taps
            if(!list.get(i).active){
                //removes current alcohol from list
                Push(list.get(i));
                list.remove(i);
                //make sure this does not cause any null
                //errors for this list
                i--;
                //increments miss counter
                misses++;
                //darkens button if list is empty
                if(list.size() < 1 && !buttons[id].silouhette)
                    buttons[id].silouhette = true;
            }
        }
    }

    //updates current score, misses, and time
    private void updateText(Canvas canvas){
        //sets paint color and style
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 128, 0));
        //draws UI HUD Rect
        canvas.drawRect(0, 0, g_width, g_height/5, paint);

        //changes paint color
        paint.setColor(Color.BLUE);
        paint.setTextSize(20*g_lowest/320);

        //currentScore is too long of a text compared to Score
        //At 600 pixels wide

        //draws current results on screen
        canvas.drawText("Score - " + Integer.toString(currentScore), 0, g_height/20, paint);
        canvas.drawText("Misses - " + Integer.toString(misses), g_width*9/20, g_height/20, paint);
        //draws current time spent below
        canvas.drawText(timeText, 0, g_height*3/20, paint);
    }

    //makes sure moving sprites remaing "exciting" for the player
    private void genRandomSpeed(){
        //rngResults = random.nextInt(10)
        switch(rngResults = (int)(Math.random()*10)){
            //has a 10% chance to trigger this case
            case 1: randomSpeed = 1; break;
            //has a 10% chance to trigger this case
            case 2:
                randomSpeed = 1;
                //As time progresses, random speed increase will be bigger
                if(scoreThreshold >= 1050) randomSpeed++;
                break;
            //has a 10% chance to trigger this case
            case 3:
                randomSpeed = 2;
                //As time progresses, random speed increase will be bigger
                if(scoreThreshold >= 1050) randomSpeed++;
                break;
            //has a 10% chance to trigger this case
            case 4:
                randomSpeed = 2;
                //As time progresses, random speed increase will be bigger
                if(scoreThreshold >= 1050) randomSpeed++;
                if(scoreThreshold >= 1650) randomSpeed++;
                break;
            //has a 10% chance to trigger this case
            case 5:
                randomSpeed = 3;
                //As time progresses, random speed increase will be bigger
                if(scoreThreshold >= 1050) randomSpeed++;
                if(scoreThreshold >= 1650) randomSpeed++;
                break;
            //has a 50% chance to have no random increase
            default: randomSpeed = 0; break;
        }
        //Log.d("RNG - ", String.valueOf(rngResults));
    }
}
