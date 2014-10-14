package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by AppDev on 14/10/2014.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AppDev on 12/10/2014.
 */
public class GameView extends SurfaceView
{
    private String TAG = "GameView";
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Rectangle rectangle, rectangle2, rectangle3;
    private List<Rectangle> rectangles = new ArrayList<Rectangle>();
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private long lastClick;
    private long gameTime;

    private int blockWidth;
    private int blockHeight;
    private static int ROWS = 3;
    private static int COLS = 5;

    private int gameSpeed;

    public GameView(Context context)
    {
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
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                createRectangles();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        gameTime = System.currentTimeMillis();
    }


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

    /*
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

    @Override
    protected void onDraw(Canvas canvas)
    {
        // set background color
        canvas.drawColor(Color.WHITE);

        // if you are using sprite class to represent
        // objects use sprite.onDraw()
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

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (System.currentTimeMillis() - lastClick > 500)
        {
            lastClick = System.currentTimeMillis();

            synchronized (getHolder())
            {
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
            }
        }
        return true;
    }

}
