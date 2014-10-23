package uc.edu.itp.drugandalcohol;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import java.net.UnknownServiceException;

import uc.edu.itp.drugandalcohol.fragments.UserDetailsFragment;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
//public class ApplicationTest extends ApplicationTestCase<Application>
//public class ApplicationTest extends ActivityInstrumentationTestCase2<UserDetailsActivity>
public class ApplicationTest extends Fr
{
    public ApplicationTest()
    {
        super(UserDetailsActivity.class);
    }
}