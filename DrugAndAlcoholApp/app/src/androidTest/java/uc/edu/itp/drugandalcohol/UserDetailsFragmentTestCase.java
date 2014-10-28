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

    /* do a test to see of the fragment is created correctly */
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
    public void testString() throws Exception
    {
        String testString = mUserDetailsActivity.userDetailsFragment.testMethod();

        // assertEquals(expected, actual) will check expected string
        // value with actual string returned from method
        Assert.assertEquals("This is the test", testString);
    }

}