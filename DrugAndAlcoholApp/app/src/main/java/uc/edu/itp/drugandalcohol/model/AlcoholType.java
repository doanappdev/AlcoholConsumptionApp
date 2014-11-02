package uc.edu.itp.drugandalcohol.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class represents the different types of drinks the user
 * might consume
 */
public abstract class AlcoholType
{
    // this the name of the XML file which is used to save values to shared preferences
    public static final String DRINK_PREF_FILE_NAME = "DrinksConsumedPrefFile";


    Context mContext;
    SharedPreferences drinksSharedPrefs;


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
        drinksSharedPrefs = mContext.getSharedPreferences(DRINK_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public abstract void setTotalDrinksConsumed(float drink1, float drink2, float drink3, float drink4);

    // save values to shared preferences so values are not deleted when app closes
    public abstract void saveTotalDrinksConsumed();



}
