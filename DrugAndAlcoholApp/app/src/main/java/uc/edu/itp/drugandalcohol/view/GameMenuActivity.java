package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import uc.edu.itp.drugandalcohol.MainActivity;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.reactiontest.GameSettings;

public class GameMenuActivity extends Activity {

    //settings variable
    final int resultStart = 1;
    final int resultSetting = 2;
    boolean speedByTimer;
    boolean randomiseSpeed;
    int score;
    int hits;
    int misses;
    String hitTNT;
    String textTime;

    Button gameViewBtn;
    Button settingsBtn;
    Button instructionsBtn;
    Button highScoreBtn;
    Button mainMenuBtn;

    public Intent resultsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        speedByTimer = false;
        randomiseSpeed = true;

        gameViewBtn = (Button)findViewById(R.id.btnStartGame);

        gameViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reactionTestIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);

                //settings.setSpeedByTimer(speedByTimer);
                reactionTestIntent.putExtra("speedByTimer", speedByTimer);
                reactionTestIntent.putExtra("randomiseSpeed", randomiseSpeed);

                startActivityForResult(reactionTestIntent, resultStart);
            }
        });

        settingsBtn = (Button)findViewById(R.id.btnSettings);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

                //settings.setSpeedByTimer(speedByTimer);
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                settingsIntent.putExtra("speedByTimer", speedByTimer);
                settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);

                //Must have this function called so that the child
                //Activity can send back results
                startActivityForResult(settingsIntent, resultSetting);
            }
        });

        instructionsBtn = (Button)findViewById(R.id.btnInstructions);

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Game Menu Activity - ", "No instructions menu yet!");
                //Intent instructionsIntent = new Intent(getApplicationContext(), InstructionsActivity.class);
                //startActivity(instructionsIntent);
            }
        });

        highScoreBtn = (Button)findViewById(R.id.btnHighScore);

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Game Menu Activity - ", "No high score menu yet!");
                //Intent highScoreIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                //startActivity(highScoreIntent);
            }
        });

        mainMenuBtn = (Button)findViewById(R.id.btnMainMenu);

        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                //finish();
                /*synchronized (holder) {
                    //quit to mainmenu
                    ((Activity) super.getContext()).finish();
                }*/
            }
        });
    }

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
        speedByTimer = resultsIntent.getBooleanExtra("speedByTimer", false);
        randomiseSpeed = resultsIntent.getBooleanExtra("randomiseSpeed", true);

        score = resultsIntent.getIntExtra("score", -1);
        hits = resultsIntent.getIntExtra("hits", -1);
        misses = resultsIntent.getIntExtra("misses", -1);
        textTime = resultsIntent.getStringExtra("textTime");
        hitTNT = resultsIntent.getStringExtra("hitTNT");

        if(score != -1){
            resultsIntent = new Intent(getApplicationContext(), GameOverActivity.class);

            resultsIntent.putExtra("score", score);
            resultsIntent.putExtra("hits", hits);
            resultsIntent.putExtra("misses", misses);
            resultsIntent.putExtra("textTime", textTime);
            resultsIntent.putExtra("hitTNT", hitTNT);

            startActivity(resultsIntent);
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
