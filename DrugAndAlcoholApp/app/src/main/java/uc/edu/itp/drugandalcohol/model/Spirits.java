package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

import uc.edu.itp.drugandalcohol.R;

/**
 * Created by AppDeveloper on 31/10/2014.
 */
public class Spirits extends AlcoholType
{
    private static final String TAG = "Spirits";

    public static final int SPIRIT_ROW_ONE_CLICKED = 8;
    public static final int SPIRIT_ROW_TWO_CLICKED = 9;
    public static final int SPIRIT_ROW_THREE_CLICKED = 10;
    public static final int SPIRIT_ROW_FOUR_CLICKED = 11;

    private float mSpirits1Consumed;
    private float mSpirits2Consumed;
    private float mSpirits3Consumed;
    private float mSpirits4Consumed;

    public Spirits(Context context)
    {
        super(context);
    }

    // setter methods
    public void setTotalDrinksConsumed(float drink1, float drink2, float drink3, float drink4)
    {
        mSpirits1Consumed = drink1;
        mSpirits2Consumed = drink2;
        mSpirits3Consumed = drink3;
        mSpirits4Consumed = drink4;

        saveTotalDrinksConsumed();
    }

    // save values to shared preferences so values are not deleted when app closes
    @Override
    public void saveTotalDrinksConsumed()
    {
        // save values to shared preferences, values are saved as (Key, Value) pairs
        // key value is stored as string in strings.xml to allow other fragments easy access
        // to them
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putFloat(mContext.getString(R.string.BEER_ONE_KEY), mSpirits1Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_TWO_KEY), mSpirits2Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_THREE_KEY), mSpirits3Consumed);
        editor.putFloat(mContext.getString(R.string.BEER_FOUR_KEY), mSpirits4Consumed);
        editor.apply();     // apply writes data in the background
        //editor.commit();    // commit writes its data to persistent storage immediately
    }
}
