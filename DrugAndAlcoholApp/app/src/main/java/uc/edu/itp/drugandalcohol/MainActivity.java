package uc.edu.itp.drugandalcohol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;

import uc.edu.itp.drugandalcohol.controller.LocationDataAdapter;
import uc.edu.itp.drugandalcohol.model.LocationData;
import uc.edu.itp.drugandalcohol.view.CalculateBACActivity;
import uc.edu.itp.drugandalcohol.view.EmergencyActivity;
import uc.edu.itp.drugandalcohol.view.GameMenuActivity;
import uc.edu.itp.drugandalcohol.view.MapActivity;
import uc.edu.itp.drugandalcohol.view.ReactionTestActivity;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;


public class MainActivity extends Activity
{
    // TAG is identifier when printing to the logcat for
    // debugging/testing purposes
    private final String TAG = "MainActivity";

    ImageButton detailsImgBtn, calculateImgBtn, emergencyImgBtn,
            gameMenuImgBtn, proximityImgBtn;

    boolean EULA_ACCEPTED = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        detailsImgBtn = (ImageButton)findViewById(R.id.imgBtnEnterDetails);
        calculateImgBtn = (ImageButton)findViewById(R.id.imgBtnCalculateBAC);
        emergencyImgBtn = (ImageButton)findViewById(R.id.imgBtnEmergencySMS);
        gameMenuImgBtn = (ImageButton)findViewById(R.id.imgBtnReactionTest);
        proximityImgBtn = (ImageButton)findViewById(R.id.imgBtnProximity);

        startEULA();

        detailsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userDetailsIntent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                startActivity(userDetailsIntent);
            }
        });

        calculateImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calculateBACIntent = new Intent(getApplicationContext(), CalculateBACActivity.class);
                startActivity(calculateBACIntent);
            }
        });

        emergencyImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emergencyIntent = new Intent(getApplicationContext(), EmergencyActivity.class);
                startActivity(emergencyIntent);
            }
        });

        gameMenuImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameMenuIntent = new Intent(getApplicationContext(), GameMenuActivity.class);
                startActivity(gameMenuIntent);
            }
        });

        proximityImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proximityIntent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(proximityIntent);
            }
        });


    }

    public void startEULA()
    {
        // check if user has agreed to EULA, only want to display
        // this dialog once
        if(!EULA_ACCEPTED)
        {

            // Display EULA
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // set boolean value equal to true when user
                    // clicks OK on EULA dialog
                    EULA_ACCEPTED = true;
                    dialogInterface.dismiss();
                }
            })
                    .setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //dialogInterface.cancel();
                                    onStop();
                                }
                            }
                    );

            View childView = getLayoutInflater().inflate(R.layout.fragment_eula, null);
            alertDialog.setView(childView);

            alertDialog.show();

        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        /*
            Todo: add code to save phone state and data
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
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
}
