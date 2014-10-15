package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by AppDev on 14/10/2014.
 */
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
    private final int MAIN_MENU = 0;
    private final int GAMEPLAY = 1;

    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private long lastClick;
    private long currentTime;
    private long previousTime;

    private GameComponent screens[];
    private int currentScreen;

    //THROWAWAY VARIABLES
    /*
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

    public GameView(Context context){
        super(context);

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
            }
        });

        //init();
    }

    private void init(){
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alcohol_sprites);

        screens = new GameComponent[]{
                new MainMenu(),
                new GameplayFunction(this, bmp)
        };

        //currentScreen = MAIN_MENU;
        currentScreen = GAMEPLAY;

        currentTime = System.currentTimeMillis();
        previousTime = currentTime;

        screens[currentScreen].reset(currentTime);
        //These do not need to be called now
        /*currentTime = previousTime;
        for(int i = 0; i < screens.length; i++)
            screens[i].reset(currentTime);*/
    }

    @Override
    protected void onDraw(Canvas canvas){
        // set background color
        canvas.drawColor(Color.WHITE);

        currentTime = System.currentTimeMillis();
        screens[currentScreen].update(currentTime, previousTime);
        screens[currentScreen].onDraw(canvas);
        // if you are using sprite class to represent
        // objects use sprite.onDraw()
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (System.currentTimeMillis() - lastClick > 300){
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()){
                screens[currentScreen].onTouchEvent(event);
            }
        }
        return true;
    }

    //THROWAWAY CODE BELOW
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
