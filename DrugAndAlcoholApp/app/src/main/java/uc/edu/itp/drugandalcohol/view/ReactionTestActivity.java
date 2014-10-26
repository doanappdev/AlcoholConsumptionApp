package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.reactiontest.GameSettings;
import uc.edu.itp.drugandalcohol.reactiontest.GameView;

public class ReactionTestActivity extends Activity {

    boolean speedByTimer;
    boolean randomiseSpeed;
    GameSettings settings;
    GameView gView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        speedByTimer = intent.getBooleanExtra("speedByTimer", false);
        randomiseSpeed = intent.getBooleanExtra("randomiseSpeed", true);

        settings = new GameSettings(speedByTimer, randomiseSpeed);

        // hide action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gView = new GameView(this, settings);
        setContentView(gView);
    }

    public void isFinished(int score, int hits, int misses, String hitTNT, String timeText){
        intent.putExtra("score",score);
        intent.putExtra("hits",hits);
        intent.putExtra("misses",misses);
        intent.putExtra("hitTNT",hitTNT);
        intent.putExtra("textTime",timeText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed()
        gView.closeGame();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reaction_test, menu);
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
