package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

import uc.edu.itp.drugandalcohol.R;

/**
 * Created by AppDeveloper on 31/10/2014.
 */
public class Wine extends AlcoholType
{
    private static final String TAG = "Beer";

    public static final int WINE_ROW_1_CLICKED = 4;
    public static final int WINE_ROW_2_CLICKED = 5;
    public static final int WINE_ROW_3_CLICKED = 6;
    public static final int WINE_ROW_4_CLICKED = 7;

    private float mWine1Consumed;
    private float mWine2Consumed;
    private float mWine3Consumed;
    private float mWine4Consumed;

    //public static final String WINE_SPARKLING_KEY = "sparklingWine";

    public Wine(Context context)
    {
        super(context);
    }

    // setter methods
    public void setTotalDrinksConsumed(float drink1, float drink2, float drink3, float drink4)
    {
        mWine1Consumed = drink1;
        mWine2Consumed = drink2;
        mWine3Consumed = drink3;
        mWine4Consumed = drink4;

        saveTotalDrinksConsumed();
    }

    // save values to shared preferences so values are not deleted when app closes
    @Override
    public void saveTotalDrinksConsumed()
    {
        // save values to shared preferences, values are saved as (Key, Value) pairs
        // key value is stored as string in strings.xml to allow other fragments easy access
        // to them
        SharedPreferences.Editor editor = drinksSharedPrefs.edit();
        editor.putFloat(mContext.getString(R.string.WINE_ONE_KEY), mWine1Consumed);
        editor.putFloat(mContext.getString(R.string.WINE_TWO_KEY), mWine2Consumed);
        editor.putFloat(mContext.getString(R.string.WINE_THREE_KEY), mWine3Consumed);
        editor.putFloat(mContext.getString(R.string.WINE_FOUR_KEY), mWine4Consumed);
        editor.apply();     // apply writes data in the background
        //editor.commit();    // commit writes its data to persistent storage immediately
    }
}
