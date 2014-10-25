package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uc.edu.itp.drugandalcohol.R;

public class MapActivity extends FragmentActivity implements LocationListener
{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private PendingIntent pendingIntent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // check if users phone (or emulator has google play services)
        checkServicesConnected();

        // click listener for short clicks
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                // remove existing marker from map
                mMap.clear();
            }
        });

    }

    /*
     * when using Google Maps v2 must check if user phone has google play services
     */
    private void checkServicesConnected()
    {
        // get Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // show status
        if(status != ConnectionResult.SUCCESS)
        {
            // Google Play Services is not available
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }
        else
        {
            //setUpMapTest();

            // Google Play Services is available
            // get reference to map
            mMap = ((SupportMapFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.mapFragment))
                    .getMap();

            // enable MyLocation layer of Google Map
            mMap.setMyLocationEnabled(true);

            // get LocationManager object from system services
            locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            // creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
            // getting the name of best provider
            String provider = locationManager.getBestProvider(criteria, true);
            // get current location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location != null)
            {
                onLocationChanged(location);
            }

            /*
             * request location updates
             * @param
             *          provider    - string value of provider
             *          timeMin     - amount of time between updates
             *          minDistance -
             *          listener    - location listener
             */
            locationManager.requestLocationUpdates(provider, 2000, 0, this);

        }
    }


    /*
     * Use this method to add markers, lines, or move camera
     * this should only be calld once and when we are sure that
     * mMap is not null
     */
    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Current Position"));
    }

    @Override
    public void onLocationChanged(Location location)
    {
        TextView tvLocation = (TextView)findViewById(R.id.txtViewLatitude);

        // get current location data
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // create LatLng object to store current location
        LatLng latLng = new LatLng(latitude, longitude);

        // show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // zoom in on the map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        // add market on map to indicate current location
        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Position"));

        // display current location coordinates in text view
        tvLocation.setText("Latitude: " + latitude + ", Longitude: " + longitude);
    }


    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
