package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

import uc.edu.itp.drugandalcohol.R;

/**
 * This class represents the different types of drinks the user
 * might consume
 */
public class AlcoholType
{
    public static final String DRINK_PREF_FILE_NAME = "DrinksConsumedPrefFile";
    // values to register which row was clicked
    public static final int BEER_ROW_1_CLICKED = 0;
    public static final int BEER_ROW_2_CLICKED = 1;
    public static final int BEER_ROW_3_CLICKED = 2;
    public static final int BEER_ROW_4_CLICKED = 3;
    public static final int WINE_ROW_1_CLICKED = 4;
    public static final int WINE_ROW_2_CLICKED = 5;
    public static final int WINE_ROW_3_CLICKED = 6;
    public static final int WINE_ROW_4_CLICKED = 7;
    public static final int SPIRIT_ONE_CLICKED = 8;
    public static final int SPIRIT_TWO_CLICKED = 9;
    public static final int SPIRIT_THREE_CLICKED = 10;
    public static final int SPIRIT_FOUR_CLICKED = 11;

    // key values for saving to shared preferences
    public static final String SMALL_BEER_KEY = "smallBeer";
    public static final String LARGE_BEER_KEY = "largeBeer";
    public static final String BEER_BOTTLE_KEY = "beerBottle";
    public static final String BEER_CAN_KEY = "beerCan";
    public static final String WINE_SPARKLING_KEY = "sparklingWine";

    // number of drinks consumed, declare as static variable so value
    // can be accessed from other fragments
    private float mSmBeersConsumed;
    private float mLgBeersConsumed;
    private float mBottleBeersConsumed;
    private float mCanBeersConsumed;

    Context mContext;
    SharedPreferences sharedPrefs;


    // standard drink value
    // e.g. 1 Small Beer = 1.1 standard drinks
    //      1 Lg Beer = 1.6 standard drinks
    //      1 Beer Bottle = 1.4 standard drinks
    //      1 Beer Can = 1.4 standard drinks
    double[] beerType = {1.1, 1.6, 1.4, 1.4};
    double[] wineType = {1.4, 1.6, 1.4, 8};
    double[] spiritsType = {1.2, 1, 1.5, 1.6};

    // constructor
    public AlcoholType(Context context)
    {
        mContext = context;
        // initialise shared preference object, supply the file name which is used to
        // create the XML file, where data is saved to.
        sharedPrefs = mContext.getSharedPreferences(DRINK_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    // setter methods
    public void setTotalBeersConsumed(float smBeer, float lgBeer, float beerBottles, float beerCans)
    {
        mSmBeersConsumed = smBeer;
        mLgBeersConsumed = lgBeer;
        mBottleBeersConsumed = beerBottles;
        mCanBeersConsumed = beerCans;
    }

    // getter methods
    public float getSmBeerConsumed() { return mSmBeersConsumed; }
    public float getLgBeersConsumed() { return mLgBeersConsumed; }
    public float getBottleBeerConsumed() { return mBottleBeersConsumed; }
    public float getBeerCansConsumed() { return mCanBeersConsumed; }


    // save values to shared preferences so values are not deleted when app closes
    public void saveBeerConsumed()
    {
        // save values to shared preferences, values are saved as (Key, Value) pairs
        // key value is stored as string in strings.xml to allow other fragments easy access
        // to them
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putFloat(mContext.getString(R.string.sm_beer_key), mSmBeersConsumed);
        editor.putFloat(mContext.getString(R.string.lg_beer_key), mLgBeersConsumed);
        editor.putFloat(mContext.getString(R.string.beer_bottle_key), mBottleBeersConsumed);
        editor.putFloat(mContext.getString(R.string.beer_can_key), mCanBeersConsumed);
        editor.apply();     // apply writes data in the background
        //editor.commit();    // commit writes its data to persistent storage immediately
    }



    public double getDrinkValue(int type, int index)
    {
        double drinkValue = 0;

        switch (type)
        {
            // beer type
            case 0:
                drinkValue = beerType[index];
                break;
            // wine type
            case 1:
                drinkValue = wineType[index];
                break;
            case 2:
                drinkValue = spiritsType[index];
                break;
        }

        return drinkValue;
    }

}
