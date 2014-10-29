package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import uc.edu.itp.drugandalcohol.R;

public class SettingsActivity extends Activity {

    //signal for parent activity
    //ID must be unique
    final int signal = 1;

    //game settings
    boolean speedByTimer;
    boolean randomiseSpeed;

    //buttons and switches
    Switch speedByTimerSwi;
    Switch randomiseSpeedSwi;
    Button backBtn;

    Intent settingsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //calls the parent activity to display current settings
        settingsIntent = new Intent(getApplicationContext(), GameMenuActivity.class);
        settingsIntent = getIntent();
        speedByTimer = settingsIntent.getBooleanExtra("speedByTimer", false);
        randomiseSpeed = settingsIntent.getBooleanExtra("randomiseSpeed", true);

        //Setting switch for speed by timer
        speedByTimerSwi = (Switch)findViewById(R.id.swiSpeedByTimer);
        speedByTimerSwi.setChecked(speedByTimer);
        speedByTimerSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { speedByTimer = !speedByTimer; }
        });

        //Setting switch for randomise speed
        randomiseSpeedSwi = (Switch)findViewById(R.id.swiRandomiseSpeed);
        randomiseSpeedSwi.setChecked(randomiseSpeed);
        randomiseSpeedSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { randomiseSpeed = !randomiseSpeed; }
        });

        //creates a button to return to game menu
        backBtn = (Button)findViewById(R.id.btnBack);

        //saves the current settings and returns to game menu
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    settingsIntent.putExtra("signal",signal);
                    settingsIntent.putExtra("speedByTimer", speedByTimer);
                    settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);
                    setResult(RESULT_OK, settingsIntent);
                    finish();
            }
        });
    }

    //overrides the onBackPressed function
    //so that it can send its current settings
    //over to the game menu
    @Override
    public void onBackPressed() {
        //places Game Settings inside the intent to send over to parent activity
        settingsIntent.putExtra("speedByTimer", speedByTimer);
        settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);
        //signals parent activity that data is being transferred
        setResult(RESULT_OK, settingsIntent);
        //closes this activity
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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
