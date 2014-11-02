package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

import uc.edu.itp.drugandalcohol.R;

/**
 * Created by AppDeveloper on 31/10/2014.
 */
public class Beer extends AlcoholType
{
    private static final String TAG = "Beer";

    // values to register which row was clicked
    public static final int BEER_ROW_1_CLICKED = 0;
    public static final int BEER_ROW_2_CLICKED = 1;
    public static final int BEER_ROW_3_CLICKED = 2;
    public static final int BEER_ROW_4_CLICKED = 3;

    // number of drinks consumed, declare as static variable so value
    // can be accessed from other fragments
    private float mBeer1Consumed;
    private float mBeer2Consumed;
    private float mBeer3Consumed;
    private float mBeer4Consumed;

    public Beer(Context context)
    {
        super(context);
    }


    // getter methods
    public float getBeer1Consumed() { return mBeer1Consumed; }
    public float getBeers2Consumed() { return mBeer2Consumed; }
    public float getBeer3Consumed() { return mBeer3Consumed; }
    public float getBeer4Consumed() { return mBeer4Consumed; }

    // setter methods
    public void setTotalDrinksConsumed(float drink1, float drink2, float drink3, float drink4)
    {
        mBeer1Consumed = drink1;
        mBeer2Consumed = drink2;
        mBeer3Consumed = drink3;
        mBeer4Consumed = drink4;

        // save number of drinks consumed to shared preferences
        saveTotalDrinksConsumed();
    }

    // save values to shared preferences so values are not deleted when app closes
    @Override
    public void saveTotalDrinksConsumed()
    {
        // save values to shared preferences defined in super class (AlcoholType),
        // values are saved as (Key, Value) pairs
        // key is stored as string in strings.xml to allow other fragments easy access
        // to them
        SharedPreferences.Editor editor = drinksSharedPrefs.edit();
        editor.putFloat(mContext.getString(R.string.BEER_ONE_KEY), mBeer1Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_TWO_KEY), mBeer2Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_THREE_KEY), mBeer3Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_FOUR_KEY), mBeer4Consumed);
        editor.apply();     // apply writes data in the background
        //editor.commit();    // commit writes its data to persistent storage immediately
    }
}
