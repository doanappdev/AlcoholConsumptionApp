package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import uc.edu.itp.drugandalcohol.fragments.EmergencyFragment;
import uc.edu.itp.drugandalcohol.fragments.UserDetailsFragment;
import uc.edu.itp.drugandalcohol.view.EmergencyActivity;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;

/**
 * Created by AppDeveloper on 29/10/2014.
 */
public class EmergencyFragmentTestCase extends ActivityInstrumentationTestCase2<EmergencyActivity>
{
   EmergencyActivity mEmergencyActivity;

    EmergencyFragment mEmergencyFragment;

    public EmergencyFragmentTestCase()
    {
        super(EmergencyActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mEmergencyActivity = getActivity();
        mEmergencyFragment = mEmergencyActivity.emergencyFragment;


    }

    public void testPreConditions()
    {
        assertNotNull(mEmergencyActivity);
        assertNotNull(mEmergencyFragment);
    }


    public void testContacts()
    {
        try {
            int contactPosition = 0;
            String contactTest = mEmergencyFragment.getSelectedContact(contactPosition);
            String numberTest = mEmergencyFragment.getSelectNumber(contactPosition);
            assertEquals("Bob", contactTest);
            assertEquals("0431000000", numberTest);
        }
        catch(Exception e)
        {

        }
    }

    public void testCancelButton()
    {
        try
        {
            boolean wasSent = mEmergencyFragment.getCancelButtonClick();
            assertEquals(true, wasSent);
        }
        catch(Exception e)
        {

        }
    }

    public void testSendSMS()
    {
        try
        {
            boolean wasSent = mEmergencyFragment.getSendButtonClick();
            assertEquals(true, wasSent);
        }
        catch(Exception e)
        {

        }
    }
}
