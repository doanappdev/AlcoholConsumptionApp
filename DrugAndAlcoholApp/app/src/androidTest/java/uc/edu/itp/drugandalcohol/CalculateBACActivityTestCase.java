package uc.edu.itp.drugandalcohol;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import uc.edu.itp.drugandalcohol.fragments.CalculateBACBottomFragment;
import uc.edu.itp.drugandalcohol.fragments.TabBeerFragment;
import uc.edu.itp.drugandalcohol.fragments.TabSpiritsFragment;
import uc.edu.itp.drugandalcohol.fragments.TabWineFragment;
import uc.edu.itp.drugandalcohol.view.CalculateBACActivity;

/**
 * Created by AppDeveloper on 1/11/2014.
 * This class performs unit test cases on the AlcoholTabActivity,
 * the activity uses the actionbar to display tabs
 * we can access the various tab fragments such as TabBeerFragment,
 * TabWineFragment and TabSpiritsFragment
 *
 */
public class CalculateBACActivityTestCase extends ActivityInstrumentationTestCase2<CalculateBACActivity>
{
    CalculateBACActivity mCalculateBACActivity;
    ActionBar mActionBar;

    // top fragments
    TabBeerFragment mTabBeerFragment;
    TabWineFragment mTabWineFragment;
    TabSpiritsFragment mTabSpiritsFragment;

    // bottom fragment
    CalculateBACBottomFragment mCalculateBACBottomFragment;

    public CalculateBACActivityTestCase()
    {
        super(CalculateBACActivity.class);
    }

    /* do a test to see if the activity and fragment are created correctly */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mCalculateBACActivity = getActivity();
        mActionBar = mCalculateBACActivity.actionBar;
        mTabBeerFragment = mCalculateBACActivity.beerTabFragment;
        mTabWineFragment = mCalculateBACActivity.wineTabFragment;
        mTabSpiritsFragment = mCalculateBACActivity.spiritsFragment;
        mCalculateBACBottomFragment = mCalculateBACActivity.calculateBACBottomFragment;

    }

    /*
        this test method will test the action bar on AlcoholTabActivity
        all tests should return a valid result
     */
    public void testValidActionBarTabs()
    {
        // check if the ActionBar is set to display true
        //assertEquals(ActionBar.NAVIGATION_MODE_TABS, mActionBar.getNavigationMode());

        // test if the mActionBar has 3 tabs (beer, wine, spirits)
        assertEquals(3, mActionBar.getNavigationItemCount());

        // get tabs on action
        ActionBar.Tab beerTab = mActionBar.getTabAt(0);
        ActionBar.Tab wineTab = mActionBar.getTabAt(1);
        ActionBar.Tab spiritsTab = mActionBar.getTabAt(2);
        // check tab is on action bar
        assertNotNull(beerTab);
        assertNotNull(wineTab);
        assertNotNull(spiritsTab);
    }

    // testing actual fragment tab
    public void testValidFragmentTabs()
    {
        // get tabs on action
        ActionBar.Tab beerTab = mActionBar.getTabAt(0);
        ActionBar.Tab wineTab = mActionBar.getTabAt(1);
        ActionBar.Tab spiritsTab = mActionBar.getTabAt(2);

        // check if tab is valid
        assertNotNull(beerTab);
        assertNotNull(wineTab);
        assertNotNull(spiritsTab);

        // set the selected tab to wine tab
        mActionBar.selectTab(wineTab);
        // check index of selected tab is equal to 1
        assertEquals(1, mActionBar.getSelectedNavigationIndex());

        // create fragment manager to access layout ID
        FragmentManager fm = getActivity().getFragmentManager();
        // assign layout ID to fragment
        Fragment currentTab = fm.findFragmentById(R.id.fragment_tab_container);
        // check object (currentTab) is not null
        assertNotNull(currentTab);
        assertTrue(currentTab instanceof TabBeerFragment);


    }

    // example test string to check if we can access methods in
    // TabBeerFragment, TabWineFragment, TabSpiritsFragment
    public void testBeerTabString() throws Exception
    {
        // call getBeerTabStringTest() from TabBeerFragment, we created mTabBeerFragment
        // in setUp() using mCalculateBACActivity so we have direct access to tab
        String testString = mTabBeerFragment.getBeerTabStringTest();

        // assertEquals(expected, actual) will check expected string
        // value with actual string returned from method
        Assert.assertEquals("Testing beer tab", testString);
    }

    public void testWineTabString() throws Exception
    {
        String testString = mTabWineFragment.getWineTabStringTest();

        // assertEquals(expected, actual) will check expected string
        // value with actual string returned from method
        Assert.assertEquals("Testing wine tab", testString);
    }

    public void testSpiritsTabString() throws Exception
    {
        String testString = mTabSpiritsFragment.getSpiritsTabStringTest();

        // assertEquals(expected, actual) will check expected string
        // value with actual string returned from method
        Assert.assertEquals("Testing spirits tab", testString);
    }


    /*
     * this test case fails if the bacValue does not equal 0.05
     * the run dialog the test returns a line similar to this:
     *
     * junit.framework.AssertionFailedError: expected:<0.05> but was:<0.04726891>
     *
     * this lets us know what the actual bacValue is <0.04726891>
     *
     * if we set:
     * expectedBAC = 0.04726891f the test will pass
     * expectedBAC = 0.05f the test will fail
     */
    public void testBACBottomFragmentFormula() throws Exception
    {
        // parameters for testBACFormula() are:
        // @numberOfDrinks
        // @hoursDrinking
        // @weight (kg)
        // @gender (male = true, female = false)
        float bacValue = mCalculateBACBottomFragment.testBACFormula(3f, 1f, 70.0f, true);
        float expectedBAC = 0.05f;

        // return true if bacValue equals 0.05
        Assert.assertEquals(expectedBAC, bacValue);
    }





}
