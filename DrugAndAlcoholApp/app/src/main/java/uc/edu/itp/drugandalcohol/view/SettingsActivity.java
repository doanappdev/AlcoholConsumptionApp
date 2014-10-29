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

        //Setting switch
        speedByTimerSwi = (Switch)findViewById(R.id.swiSpeedByTimer);
        speedByTimerSwi.setChecked(speedByTimer);
        speedByTimerSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { speedByTimer = !speedByTimer; }
        });

        //Setting switch
        randomiseSpeedSwi = (Switch)findViewById(R.id.swiRandomiseSpeed);
        randomiseSpeedSwi.setChecked(randomiseSpeed);
        randomiseSpeedSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { randomiseSpeed = !randomiseSpeed; }
        });

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

    /*
        using standard Intent bundle to pass user settings between 2 activities.
        Eric you did not create this technique, the putExtra() is part
        of the API, you can't take credit for it as you said in your message in
        Source Tree, what you have done is used their method to pass data.
        When you said you created a way to to pass data I was expecting you to
        have used your own class not the Intent object. You cant take credit for some one else's work!
        (this is just so you know for next time)
     */
    @Override
    public void onBackPressed() {
        /*settingsIntent = new Intent(getApplicationContext(),
                GameMenuActivity.class);*/
        settingsIntent.putExtra("speedByTimer", speedByTimer);
        settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);
        setResult(RESULT_OK, settingsIntent);
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
