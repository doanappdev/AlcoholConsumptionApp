package uc.edu.itp.drugandalcohol;

import android.test.ActivityInstrumentationTestCase2;

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
    }
}
