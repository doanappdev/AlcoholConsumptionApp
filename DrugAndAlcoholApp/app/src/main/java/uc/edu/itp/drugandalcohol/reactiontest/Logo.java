package uc.edu.itp.drugandalcohol.reactiontest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by wh0-r-u on 16/10/2014.
 */

/*
*
* UNUSED AND UNNECESSARY CLASS
*
*/

public class Logo extends Sprite {

    public Logo(GameView view, Bitmap bmp, final int columns, final int rows)
    {
        super(view, 0, bmp, columns, rows);

        x = 80;
        y = 30;
        srcX = 0;
        srcY = 0;

        src = new Rect(srcX, srcY, srcX + width, srcY + height);
        dst = new RectF(x, y, x + width * 2, y + height * 2);
    }

    //@Override
    //public void update() {
        //Should not be implemented
    //}

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
