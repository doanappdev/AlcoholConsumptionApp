package uc.edu.itp.drugandalcohol;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

import java.net.UnknownServiceException;

import uc.edu.itp.drugandalcohol.fragments.UserDetailsFragment;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
//public class ApplicationTest extends ApplicationTestCase<Application>
public class ApplicationTest extends ActivityInstrumentationTestCase2<UserDetailsActivity>
{
    UserDetailsActivity userDetailsActivity;
    UserDetailsFragment userDetailsFragment;

    public ApplicationTest()
    {
        super(UserDetailsActivity.class);
    }

    /* do a test to see of the fragment is created correctly */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        userDetailsActivity = getActivity();
        userDetailsFragment = userDetailsActivity.userDetailsFragment;
    }

    public void testPreConditions()
    {
        assertNotNull(userDetailsActivity);
        assertNotNull(userDetailsFragment);
    }

    /*
        this test will throw error because actual string is different to
        expected string
     */
    public void testString() throws Exception
    {
        String testString = userDetailsActivity.userDetailsFragment.testMethod();

        // assertEquals(expected, actual) will check expected string
        // value with actual string returned from method
        Assert.assertEquals("This is the test", testString);
    }

}