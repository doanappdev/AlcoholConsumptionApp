package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import uc.edu.itp.drugandalcohol.MainActivity;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.reactiontest.GameSettings;

public class GameMenuActivity extends Activity {

    boolean speedByTimer;
    boolean randomiseSpeed;

    Switch speedIncrementSwi;
    Switch randomiseSpeedSwi;
    Button gameViewBtn;
    Button instructionsBtn;
    Button highScoreBtn;
    Button mainMenuBtn;

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

                startActivity(reactionTestIntent);
            }
        });

        instructionsBtn = (Button)findViewById(R.id.btnInstructions);

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent instructionsIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                startActivity(instructionsIntent);
            }
        });

        highScoreBtn = (Button)findViewById(R.id.btnHighScore);

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent highScoreIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                startActivity(highScoreIntent);
            }
        });

        mainMenuBtn = (Button)findViewById(R.id.btnMainMenu);

        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                /*synchronized (holder) {
                    //quit to mainmenu
                    ((Activity) super.getContext()).finish();
                }*/
            }
        });

        speedIncrementSwi = (Switch)findViewById(R.id.swiSpeedIncrement);
        speedIncrementSwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { speedByTimer = !speedByTimer; }
        });

        randomiseSpeedSwi = (Switch)findViewById(R.id.swiRandomiseSpeed);
        randomiseSpeedSwi.setChecked(true);
        randomiseSpeedSwi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) { randomiseSpeed = !randomiseSpeed; }
    });
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
