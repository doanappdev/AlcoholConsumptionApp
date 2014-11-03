package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import uc.edu.itp.drugandalcohol.view.CalculateBACActivity;
import uc.edu.itp.drugandalcohol.view.GameMenuActivity;

/**
 * Created by AppDeveloper on 3/11/2014.
 */
public class GameMenuActivityTestCase extends ActivityInstrumentationTestCase2<GameMenuActivity>
{
    GameMenuActivity mGameMenuActivity;

    public GameMenuActivityTestCase()
    {
        super(GameMenuActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        mGameMenuActivity = new GameMenuActivity();
    }

    /*
     * this is just an example of how to access the methods
     * in the GameMenuActivity
     */
    public void testGameMenuString() throws Exception
    {
        // testGameMenuString() returns a string
        String expected = mGameMenuActivity.testGameMenuString();
        String actual = "This is a string from Game Menu";


        // check if the strings are equal
        Assert.assertEquals(actual, expected);
    }


}


