package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

import uc.edu.itp.drugandalcohol.MainActivity;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.reactiontest.GameSettings;

public class GameMenuActivity extends Activity {

    //settings variable. must be different from each other
    // so that intents can recognise different activities.
    final int resultStart = 1;
    final int resultSetting = 2;

    //game settings
    boolean speedByTimer;
    boolean randomiseSpeed;

    //Game Score data
    int score;
    int hits;
    int misses;
    String hitTNT;
    String textTime;

    //menu buttons
    Button gameViewBtn;
    Button settingsBtn;
    Button instructionsBtn;
    Button highScoreBtn;
    Button mainMenuBtn;

    //used to send and receive results
    public Intent resultsIntent;

    //Creates the main menu for the reaction test
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        //sets the default game settings
        speedByTimer = false;
        randomiseSpeed = true;

        gameViewBtn = (Button)findViewById(R.id.btnStartGame);

        gameViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creates a signal so that reaction test activity can send back score results
                Intent reactionTestIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);

                //This sends game settings to the reaction test
                reactionTestIntent.putExtra("speedByTimer", speedByTimer);
                reactionTestIntent.putExtra("randomiseSpeed", randomiseSpeed);

                //Must have this function called so that the child
                //Activity can send back results
                startActivityForResult(reactionTestIntent, resultStart);
            }
        });

        settingsBtn = (Button)findViewById(R.id.btnGameSettings);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creates a signal so that settings activity can send back changed settings
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

                //This sends current settings to the settings menu
                settingsIntent.putExtra("speedByTimer", speedByTimer);
                settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);

                //Must have this function called so that the child
                //Activity can send back results
                startActivityForResult(settingsIntent, resultSetting);
            }
        });

        instructionsBtn = (Button)findViewById(R.id.btnGameInstructions);

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Game Menu Activity - ", "No instructions menu yet!");
                //Intent instructionsIntent = new Intent(getApplicationContext(), InstructionsActivity.class);
                //startActivity(instructionsIntent);
            }
        });

        highScoreBtn = (Button)findViewById(R.id.btnGameHighScore);

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creates a dummy high score activity
                Intent highScoreIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(highScoreIntent);
            }
        });

        mainMenuBtn = (Button)findViewById(R.id.btnBackMainMenu);

        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //functions as if user is pressing the back button
                onBackPressed();
            }
        });
    }

    //This function is implemented so that
    //main menu button can call it
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    //This is used to receive message from child activity
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        resultsIntent = data;

        //makes sure this function execute right orders
        if(resultsIntent.getIntExtra("signal", 0) == 0)
        {
            //set game score data from reaction test
            score = resultsIntent.getIntExtra("score", -1);
            hits = resultsIntent.getIntExtra("hits", -1);
            misses = resultsIntent.getIntExtra("misses", -1);
            textTime = resultsIntent.getStringExtra("textTime");
            hitTNT = resultsIntent.getStringExtra("hitTNT");

            //create a game over activity for this case
            resultsIntent = new Intent(getApplicationContext(), GameOverActivity.class);

            //send game score data to game over menu
            resultsIntent.putExtra("score", score);
            resultsIntent.putExtra("hits", hits);
            resultsIntent.putExtra("misses", misses);
            resultsIntent.putExtra("textTime", textTime);
            resultsIntent.putExtra("hitTNT", hitTNT);

            //start it
            startActivity(resultsIntent);
        }
        else
        {
            //set game settings from settings activity
            speedByTimer = resultsIntent.getBooleanExtra("speedByTimer", false);
            randomiseSpeed = resultsIntent.getBooleanExtra("randomiseSpeed", true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);
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
