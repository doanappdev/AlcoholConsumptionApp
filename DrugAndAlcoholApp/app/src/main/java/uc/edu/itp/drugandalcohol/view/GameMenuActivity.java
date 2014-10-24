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

    //settings variable
    final int result = 1;
    boolean speedByTimer;
    boolean randomiseSpeed;

    Button gameViewBtn;
    Button settingsBtn;
    Button instructionsBtn;
    Button highScoreBtn;
    Button mainMenuBtn;

    public Intent settingsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

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

        settingsBtn = (Button)findViewById(R.id.btnSettings);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

                //settings.setSpeedByTimer(speedByTimer);
                settingsIntent.putExtra("speedByTimer", speedByTimer);
                settingsIntent.putExtra("randomiseSpeed", randomiseSpeed);

                //Must have this function called so that the child
                //Activity can send back results
                startActivityForResult(settingsIntent, result);
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
    }

    //This is used to receive message from child activity
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        settingsIntent = data;
        speedByTimer = settingsIntent.getBooleanExtra("speedByTimer", false);
        randomiseSpeed = settingsIntent.getBooleanExtra("randomiseSpeed", true);
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
