package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

import uc.edu.itp.drugandalcohol.R;

/**
 * This class represents the different types of drinks the user
 * might consume
 */
public abstract class AlcoholType
{
    public static final String DRINK_PREF_FILE_NAME = "DrinksConsumedPrefFile";


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

    // default constructor
    public AlcoholType(){}

    // constructor
    public AlcoholType(Context context)
    {
        mContext = context;
        // initialise shared preference object, supply the file name which is used to
        // create the XML file, where data is saved to.
        sharedPrefs = mContext.getSharedPreferences(DRINK_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public abstract void setTotalDrinksConsumed(float drink1, float drink2, float drink3, float drink4);

    // save values to shared preferences so values are not deleted when app closes
    public abstract void saveTotalDrinksConsumed();

        // save values to shared preferences, values are saved as (Key, Value) pairs
        // key value is stored as string in strings.xml to allow other fragments easy access
        // to them
        //SharedPreferences.Editor editor = sharedPrefs.edit();
        //editor.putFloat(mContext.getString(R.string.sm_beer_key), mSmBeersConsumed);
        //editor.putFloat(mContext.getString(R.string.lg_beer_key), mLgBeersConsumed);
        //editor.putFloat(mContext.getString(R.string.beer_bottle_key), mBottleBeersConsumed);
        //editor.putFloat(mContext.getString(R.string.beer_can_key), mCanBeersConsumed);
        //editor.apply();     // apply writes data in the background
        //editor.commit();    // commit writes its data to persistent storage immediately



    /*
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
    */

}
