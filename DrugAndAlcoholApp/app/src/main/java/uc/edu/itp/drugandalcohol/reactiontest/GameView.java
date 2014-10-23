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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import uc.edu.itp.drugandalcohol.R;


/**
 * Created by AppDev on 12/10/2014.
 */
public class GameView extends SurfaceView
{
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private GameplayFunction gameplay;
    private long lastClick;
    private long currentTime;
    private long previousTime;

    private boolean isClosed;
    private boolean speedByTimer;

    public GameView(Context context, boolean speedByTimer){
        super(context);

        this.speedByTimer = speedByTimer;

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
            public void surfaceCreated(SurfaceHolder holder){
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

    private void init(){
        isClosed = false;

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alcohol_sprites);

        gameplay = new GameplayFunction(this, bmp, speedByTimer);

        currentTime = System.currentTimeMillis();
        previousTime = currentTime;

        gameplay.reset(currentTime);
    }

    @Override
    protected void onDraw(Canvas canvas){
        // set background color
        if(isClosed) {
            previousTime = currentTime;
            gameplay.clean();
            doLose();
        }else{
            canvas.drawColor(Color.WHITE);
            currentTime = System.currentTimeMillis();

            gameplay.update(currentTime, previousTime);
            gameplay.onDraw(canvas);

            isClosed = gameplay.condition();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){ //300
        if (System.currentTimeMillis() - lastClick > 100){
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()){
                isClosed = gameplay.onTouchEvent(event);
                /*if(isClosed){
                    previousTime = currentTime;
                    gameplay.clean();
                    doLose();
                }*/
            }
        }
        return true;
    }

    public void doLose() {
        synchronized (holder) {
            //quit to mainmenu
            ((Activity) super.getContext()).finish();
        }
    }

    //THROWAWAY VARIABLES
    /*
    public final int MAIN_MENU = 0;
    public final int INSTRUCTIONS = 1;
    public final int GAMEPLAY = 2;
    public final int GAME_OVER = 3;
    public int currentScreen;
    public bool isClosed;
    private bool condition;

    //private Bitmap bmps[];

    ///private GameComponent screens[];
    //private GameplayFunction scoreKeeper;

    private int gameSpeed; //This one is used in a different class
    private String TAG = "GameView";
    private int blockWidth;
    private int blockHeight;
    private static int ROWS = 3;
    private static int COLS = 5;
    private Rectangle rectangle, rectangle2, rectangle3;
    private List<Rectangle> rectangles = new ArrayList<Rectangle>();
    private List<TestSprite> sprites = new ArrayList<TestSprite>();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    */

    //Throwaway Init() code
    /*bmps = new Bitmap[3];
    bmps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.title);
    bmps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_buttons);
    bmps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.alcohol_sprites);*/

    //scoreKeeper = new GameplayFunction(this, bmps[2]);

    /*screens = new GameComponent[]{
            new MainMenu(this, bmps[0], bmps[1]),
            new InstructionsMenu(),
            scoreKeeper,
            new GameOverMenu(this, bmps[1], scoreKeeper)
    };*/

    //currentScreen = MAIN_MENU;
    //currentScreen = GAMEPLAY;
    //screens[currentScreen].reset(currentTime);

    //throwaway onDraw() code
    /*screens[currentScreen].update(currentTime, previousTime);
    screens[currentScreen].onDraw(canvas);

    condition = screens[currentScreen].condition();
    if(condition){
        //switchMenus();
        if(currentScreen == GAMEPLAY){
            scoreKeeper.clean();
            currentScreen = GAME_OVER;
        }
        previousTime = currentTime;
        screens[currentScreen].reset(currentTime);
    }
    if(isClosed)
        doLose();
    */

    //Throwaway onTouchEvent() code
    /*
    condition = screens[currentScreen].onTouchEvent(event);
    if(condition){
        previousTime = currentTime;
        screens[currentScreen].reset(currentTime);
    }
    */

    //THROWAWAY CODE BELOW
     /*private void switchMenus(){
        switch(currentScreen){
            case MAIN_MENU:
                break;
            case INSTRUCTIONS:
                break;
            case GAMEPLAY:
                break;
            case GAME_OVER:
                break;
            default:
                break;
        }
    }*/

    /*
    for(Rectangle rect : rectangles)
    {
        rect.onDraw(canvas, gameSpeed);
    }

    // increase game speed every 7 seconds
    if(System.currentTimeMillis() - gameTime > 7000)
    {

        gameSpeed += 5;
        gameTime = System.currentTimeMillis();
    }

    for (int i = rectangles.size() - 1; i >= 0; i--)
    {
        Rectangle rect = rectangles.get(i);
        // check if touch event pos is on
        // rectangle
        if (rect.isClickedOn(event.getX(), event.getY()))
        {
            rectangles.remove(rect);
            break;
        }
    }
    */

    /*
    private void createRectangles()
    {
        // generate random number between 0-2
        //Random random = new Random();
        //int pos = random.nextInt(3);

        // row 0
        rectangles.add(rectangle = new Rectangle(this, 0, -427, Color.RED));
        rectangles.add(rectangle = new Rectangle(this, 240, -427, Color.BLUE));
        rectangles.add(rectangle = new Rectangle(this, 480, -427, Color.YELLOW));

        // row 1
        rectangles.add(rectangle = new Rectangle(this, 0, 0, Color.RED));
        rectangles.add(rectangle = new Rectangle(this, 240, 0, Color.BLUE));
        rectangles.add(rectangle = new Rectangle(this, 480, 0, Color.YELLOW));

        // row 2
        rectangles.add(rectangle = new Rectangle(this, 0, 427, Color.YELLOW));
        rectangles.add(rectangle = new Rectangle(this, 240, 427, Color.RED));
        rectangles.add(rectangle = new Rectangle(this, 480, 427, Color.BLUE));

        // row 3
        rectangles.add(rectangle = new Rectangle(this, 0, 854, Color.RED));
        rectangles.add(rectangle = new Rectangle(this, 240, 854, Color.BLUE));
        rectangles.add(rectangle = new Rectangle(this, 480, 854, Color.YELLOW));

        gameSpeed = 5;

    }


     Example code to load images
    private void createSprites() {
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));

    }

    private Sprite createSprite(int resource)
    {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
    }

    */
}
