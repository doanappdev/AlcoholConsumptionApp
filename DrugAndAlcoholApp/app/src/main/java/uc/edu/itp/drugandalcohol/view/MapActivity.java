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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// import required to connect to Microsoft Azure Cloud Services
import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.ToDoItem;

public class MapActivity extends FragmentActivity implements LocationListener, View.OnClickListener
{
    private String TAG = "MapActivity";

    private MobileServiceClient mClient;
    // table name on cloud, did not have time to change settings provided
    // by Microsoft, to change database name access web services project in
    // Visual Studio and change relevant details
    private MobileServiceTable mToDoItemTable;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private PendingIntent pendingIntent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Button saveLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        saveLocationBtn = (Button)findViewById(R.id.btnSaveLocation);
        saveLocationBtn.setOnClickListener(this);

        // check if users phone or emulator has google play services
        checkServicesConnected();
        connectAzureCloud();

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


    public void connectAzureCloud()
    {
        try
        {
            // Create the Mobile Service Client instance, using the
            // Mobile Service URL and Key provided from Azure website
            // after set up cloud services
            mClient = new MobileServiceClient(
                    "https://alcohol-consumption-app-data.azure-mobile.net/",
                    "TJTOXgFJpedXaWKGLdMjHOngavMuZe52",
                    this
            );

            // log connection succeeded
            Log.i(TAG, "Connection to cloud succeeded");
        }
        catch (MalformedURLException e)
        {
            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
            // log connection succeeded
            Log.d(TAG, "Connection failed - " + e.toString());
        }
    }

   @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnSaveLocation:
                saveLocation();
        }
    }

    public void saveLocation()
    {
        // using ToDOItem object because this is the table name provided by Microsoft when creating
        // a SQL database following their tutorial. We can change the table name if we want to
        // You have to change the settings in the Visual Studio Web services project provided by
        // microsoft, due to time running out of time we decided not to change the name of the table
        // this code is for demonstration on how to connect to cloud to save location data to
        // database. Hopefully the next group can work on implementing this function
        ToDoItem item = new ToDoItem();
        item.id = "test1@email.com";            // we can supply the users email as the ID this will help when retrieving data
        item.text = "223.8976 554.567";
        mClient.getTable(ToDoItem.class).insert(item, new TableOperationCallback<ToDoItem>() {
            @Override
            public void onCompleted(ToDoItem toDoItem, Exception e, ServiceFilterResponse serviceFilterResponse) {
                if (e == null) {
                    // Insert succeeded
                    Toast.makeText(getApplicationContext(), "Inserting Location Data to table Succeeded", Toast.LENGTH_LONG).show();
                } else {
                    // Insert failed
                    Toast.makeText(getApplicationContext(), "Inserting Location Data to table Failed", Toast.LENGTH_LONG).show();
                    Log.d(TAG, e.toString());
                }
            }
        });
    }


    /*
     * Use this method to add markers, lines, or move camera
     * this should only be called once and when we are sure that
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
        Uncomment to add code to the activities menu bar
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
