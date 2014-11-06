package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import uc.edu.itp.drugandalcohol.view.MapActivity;

/**
 * Created by Robyn on 5/11/2014.
 */
public class MainActivityTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mainActivity;

    public MainActivityTestCase(){super(MainActivity.class);}

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mainActivity = getActivity();
    }

    public void testPreConditions()
    {
        assertNotNull(mainActivity);
    }

    public void testDetailsButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }

    public void testCalculateButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }

    public void testEmergencyButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }

    public void testGameButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }

    public void testProximityButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }

    public void testDeleteButton()
    {
        boolean wasClicked = mainActivity.testDetailsButton();
        assertEquals(true,wasClicked);
    }
}
