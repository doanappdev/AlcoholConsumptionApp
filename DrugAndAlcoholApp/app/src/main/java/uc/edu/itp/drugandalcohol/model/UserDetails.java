package uc.edu.itp.drugandalcohol.model;

/**
 * Created by AppDeveloper on 27/10/2014.
 *
 * Using singleton pattern so data from this class can be
 * accessed from other activities and fragments.
 * The singleton insures that only one object can be created
 * by this class at a time.
 */
public class UserDetails
{
    // declare member variables as static
    // so data does not get lost when activity/fragment
    // closes
    private static int mAge;
    private static int mWeight;
    private static boolean mMale;
    private static boolean mPregnant;
    private static double mStandardDrinks;

    private static UserDetails mInstance = null;

    // constructor
    protected UserDetails() {}

    public static synchronized UserDetails getInstance()
    {
        // check if object for class has been created already
        if(mInstance == null) { mInstance = new UserDetails(); }

        // when UserDetails object is created we set these values to default values
        //mAge = 0;
        //mWeight = 0;
        //mMale = true;
        //mPregnant = false;
        //mStandardDrinks = 0;

        return mInstance;
    }

    // setter methods
    public void setAge(int age) { mAge = age; }
    public void setWeight(int weight) { mWeight = weight; }
    public void setGender(boolean male) { mMale = male; }
    public void setPregnant(boolean pregnant) { mPregnant = pregnant; }
    public void setStandardDrinks(double standardDrinks) { mStandardDrinks = standardDrinks; }

    // getter methods
    public int getAge() { return mAge; }
    public int getWeight() { return mWeight; }
    public boolean getGender() { return mMale; }
    public boolean getPregnant() { return mPregnant; }
    public double getStandardDrinks() { return mStandardDrinks; }

}
