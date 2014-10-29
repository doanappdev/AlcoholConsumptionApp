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

/*
This activity holds the main game. Its runs the standard Android Canvas
SurfaceView. OpenGL ES 2.0 is a very good replacement for the SurfaceView
for future modifications as it performs better and the Canvas has weird
shaking effects when game ends.
* */
public class ReactionTestActivity extends Activity {

    //signal for parent activity
    final int signal = 0;

    //Game settings
    boolean speedByTimer;
    boolean randomiseSpeed;
    GameSettings settings;

    GameView gView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This gets the settings from the Game Menu Activity. This is
        //needed for players who want to adjust their gameplay options
        intent = getIntent();
        speedByTimer = intent.getBooleanExtra("speedByTimer", false);
        randomiseSpeed = intent.getBooleanExtra("randomiseSpeed", true);

        settings = new GameSettings(speedByTimer, randomiseSpeed);

        // hide action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //the surface view becomes the main viewing content
        gView = new GameView(this, settings);
        setContentView(gView);
    }

    //This function is called from the SurfaceView when game ends
    public void isFinished(int score, int hits, int misses, String hitTNT, String timeText){
        intent.putExtra("signal",signal);
        intent.putExtra("score",score);
        intent.putExtra("hits",hits);
        intent.putExtra("misses",misses);
        intent.putExtra("hitTNT",hitTNT);
        intent.putExtra("textTime",timeText);
        setResult(RESULT_OK, intent);
        finish();
    }

    //This will properly close the game and send the results to the
    //game over menu
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
