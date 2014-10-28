package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;


import uc.edu.itp.drugandalcohol.fragments.CalculateBACTopFragment;




/**
 * Created by AppDeveloper on 28/10/2014.
 * Test class
 * to test CalculateBACTopFragment need to create a dummy activity
 * (UnitTestCalculateBACTopFragmentActivity) so we can load a single fragment
 * using FragmentManager and FragmentTransaction.
 * The CalculateBACActivity statically loads two fragments (Top & Bottom) created
 * in the XML file activity_calculate_bac.xml
 *
 * Was not able to use CalculateBACActivity to access only one of the fragments for testing
 * so we use this dummy activity
 */
public class CalculateBACTopFragmentTestCase extends ActivityInstrumentationTestCase2<DummyCalculateBACTopFragmentActivity>
{
    private DummyCalculateBACTopFragmentActivity mTestActivity;
    CalculateBACTopFragment mTopFragment;

    public CalculateBACTopFragmentTestCase()
    {
        super(DummyCalculateBACTopFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mTestActivity = getActivity();
        mTopFragment = mTestActivity.topFragment;
    }

    public void testPreConditions()
    {
        assertNotNull(mTestActivity);
        assertNotNull(mTopFragment);
    }

    /*
     * add code for unit testing below
     */

    // this test will check if the values are equal
    public void testNumberOfDrinks() throws Exception
    {
        int testNumOfDrinks = mTestActivity.topFragment.testNumberOfDrinks();

        // assertEquals(expected, actual) will check expected int
        // value with actual int returned from method
        Assert.assertEquals(10, testNumOfDrinks);
    }



}
