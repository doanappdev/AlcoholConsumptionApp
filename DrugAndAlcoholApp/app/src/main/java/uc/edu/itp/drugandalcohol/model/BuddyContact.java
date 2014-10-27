package uc.edu.itp.drugandalcohol.model;

/**
 * Created by AppDeveloper on 27/10/2014.
 *
 */
public class BuddyContact
{
    private static String mName;
    private static int mNumber;

    public BuddyContact() {}

    // setter methods
    public void setName(String name) { mName = name; }
    public void setNumber(int number) { mNumber = number; }

    // getter methods
    public String getName() { return mName; }
    public int getNumber() { return mNumber; }
}
