package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

import uc.edu.itp.drugandalcohol.model.LocationData;
import uc.edu.itp.drugandalcohol.view.MapActivity;

/**
 * Created by Robyn on 31/10/2014.
 */
public class MapTestCase extends ActivityInstrumentationTestCase2<MapActivity> {

    MapActivity mapActivity;

    public MapTestCase() {super(MapActivity.class);}

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mapActivity = getActivity();
    }

    public void testPreConditions()
    {
        assertNotNull(mapActivity);
    }

    public void testLocationButton()
    {
        try
        {
            boolean wasClicked = mapActivity.getLocationButtonClick();
            assertEquals(true, wasClicked);
        }
        catch(Exception e)
        {

        }
    }

    public void testSaveLocationButton()
    {
        try
        {
            boolean wasClicked = mapActivity.getSaveButtonClick();
            assertEquals(true, wasClicked);
        }
        catch(Exception e)
        {

        }
    }

    public void testLocationSaveData()
    {
        LocationData savedData = mapActivity.getLocation("1","23.1","45.1");
        assertEquals("1",savedData.getId());
        assertEquals("23.1",savedData.getLat());
        assertEquals("45.1",savedData.getLongitude());
    }
}
