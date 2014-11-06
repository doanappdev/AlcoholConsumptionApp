package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import uc.edu.itp.drugandalcohol.fragments.UserDetailsFragment;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
//public class ApplicationTest extends ApplicationTestCase<Application>
public class UserDetailsFragmentTestCase extends ActivityInstrumentationTestCase2<UserDetailsActivity>
{
    UserDetailsActivity mUserDetailsActivity;
    UserDetailsFragment mUserDetailsFragment;

    public UserDetailsFragmentTestCase()
    {
        super(UserDetailsActivity.class);
    }

    /* do a test to see if the fragment is created correctly */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mUserDetailsActivity = getActivity();
        mUserDetailsFragment = mUserDetailsActivity.userDetailsFragment;
    }

    public void testPreConditions()
    {
        assertNotNull(mUserDetailsActivity);
        assertNotNull(mUserDetailsFragment);
    }

    /*
        this test will throw error because actual string is different to
        expected string
     */
    public void testUserDetails()
    {
        try
        {
            //boolean worked = mUserDetailsActivity.userDetailsFragment.testSetDetails("23","60", false, false, "Bob", "0439287465");

            String age = "1";
            int result = mUserDetailsActivity.userDetailsFragment.testIncorrectAge(age);

            // assertEquals(expected, actual) will check expected string
            // value with actual string returned from method
            assertEquals(1, result);
        }
        catch(Exception e)
        {

        }
    }

    public void testSetDetailsOne()
    {
        try
        {
            boolean detailsSet = mUserDetailsActivity.userDetailsFragment.testSetDetails("23","60",false,false,"Bob","0439287465");
            assertEquals(true, detailsSet);
        }
        catch(Exception e)
        {

        }
    }

    public void testSetDetailsTwo()
    {
        try
        {
            boolean detailsSet = mUserDetailsActivity.userDetailsFragment.testSetDetails("25","80",true,false,"Fred","0439288225");
            assertEquals(true, detailsSet);
        }
        catch(Exception e)
        {

        }
    }


    public void testSetDetailsThree()
    {
        try
        {
            boolean detailsSet = mUserDetailsActivity.userDetailsFragment.testSetDetails("1","80",true,false,"Fred","0439288225");
            assertEquals(true, detailsSet);
        }
        catch(Exception e)
        {

        }
    }

    public void testCancelButton()
    {
        try
        {
            boolean cancel = mUserDetailsActivity.userDetailsFragment.testCancelButton();
            assertEquals(true, cancel);
        }
        catch(Exception e)
        {

        }
    }
}