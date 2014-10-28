package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import uc.edu.itp.drugandalcohol.fragments.CalculateBACBottomFragment;


/**
 * Created by AppDeveloper on 28/10/2014.
 */
public class CalculateBACBottomFragmentTestCase extends ActivityInstrumentationTestCase2<DummyCalculateBACBottomFragmentActivity>
{
    private DummyCalculateBACBottomFragmentActivity mTestActivity;

    CalculateBACBottomFragment mBottomFragment;

    public CalculateBACBottomFragmentTestCase()
    {
        super(DummyCalculateBACBottomFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mTestActivity = getActivity();
        mBottomFragment = mTestActivity.bottomFragment;
    }

    public void testPreConditions()
    {
        assertNotNull(mTestActivity);
        assertNotNull(mBottomFragment);
    }

    /*
     * add unit test code below
     */

    // example how to test the BAC formula
    public void testBACFormula()
    {
        boolean male = true;

        /*
         * testBACFormula() has following parameters
         *          @numOfDrinks
         *          @hrs
         *          @weight (kg)
         *          @gender (male = true, female = false)
         */
        float mBAC = mBottomFragment.testBACFormula(3.0f, 1.0f, 70.0f, true);

        Assert.assertEquals(0.05f, mBAC);
    }
}
