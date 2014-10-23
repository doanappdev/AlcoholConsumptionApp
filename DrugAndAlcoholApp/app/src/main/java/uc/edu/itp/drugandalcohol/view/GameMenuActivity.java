package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import uc.edu.itp.drugandalcohol.MainActivity;
import uc.edu.itp.drugandalcohol.R;

public class GameMenuActivity extends Activity {

    Button gameViewBtn;
    Button instructionsBtn;
    Button highScoreBtn;
    Button mainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        gameViewBtn = (Button)findViewById(R.id.btnStartGame);

        gameViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reactionTestIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                startActivity(reactionTestIntent);
            }
        });

        instructionsBtn = (Button)findViewById(R.id.btnInstructions);

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reactionTestIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                startActivity(reactionTestIntent);
            }
        });

        highScoreBtn = (Button)findViewById(R.id.btnHighScore);

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reactionTestIntent = new Intent(getApplicationContext(), ReactionTestActivity.class);
                startActivity(reactionTestIntent);
            }
        });

        mainMenuBtn = (Button)findViewById(R.id.btnMainMenu);

        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reactionTestIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(reactionTestIntent);
            }
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
