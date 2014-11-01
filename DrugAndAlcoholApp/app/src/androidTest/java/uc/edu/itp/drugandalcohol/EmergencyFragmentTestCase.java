package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import uc.edu.itp.drugandalcohol.fragments.EmergencyFragment;

/**
 * Created by AppDeveloper on 29/10/2014.
 */
public class EmergencyFragmentTestCase extends ActivityInstrumentationTestCase2<DummyEmergencyFragmentActivity>
{
    private DummyEmergencyFragmentActivity dummyEmergencyFragmentActivity;

    EmergencyFragment emergencyFragment;

    public EmergencyFragmentTestCase()
    {
        super(DummyEmergencyFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        dummyEmergencyFragmentActivity = getActivity();
        emergencyFragment = dummyEmergencyFragmentActivity.emergencyFragment;
    }

    public void testPreConditions()
    {
        assertNotNull(dummyEmergencyFragmentActivity);
        assertNotNull(emergencyFragment);
    }

    public void testInteractionUI()
    {
        /*dummyEmergencyFragmentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dummyEmergencyFragmentActivity.addItemsToSpinner();
            }
        });*/
    }

    public void testContacts()
    {
        int contactPosition = 0;
        String contactTest = emergencyFragment.getSelectedContact(contactPosition);
        String numberTest = emergencyFragment.getSelectNumber(contactPosition);
        assertEquals("Bob",contactTest);
        assertEquals("0431000000", numberTest);
    }
}
