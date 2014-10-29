package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

public class GameOverActivity extends Activity {

    //Game score Data
    int score;
    int hits;
    int misses;
    String hitTNT;
    String textTime;

    //Text and button functions
    TextView scoreTxt;
    TextView hitsTxt;
    TextView missesTxt;
    TextView hitTntTxt;
    TextView timeTxt;
    Button backBtn;
    Button highScoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2);

        //sets game score data
        Intent intent = getIntent();
        score = intent.getIntExtra("score", -1);
        hits = intent.getIntExtra("hits", -1);
        misses = intent.getIntExtra("misses", -1);
        textTime = intent.getStringExtra("textTime");
        hitTNT = intent.getStringExtra("hitTNT");

        setResultsText();

        //adds game button functions
        highScoreBtn = (Button)findViewById(R.id.btnGameHighScore);
        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spawns dummy activity
                Intent highScoreIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(highScoreIntent);
            }
        });

        //returns to game menu
        backBtn = (Button)findViewById(R.id.btnReturnGameMenu);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //sets results from test on screen
    private void setResultsText(){
        scoreTxt = (TextView)findViewById(R.id.txtScore);
        scoreTxt.setText(Integer.toString(score));

        hitsTxt = (TextView)findViewById(R.id.txtHits);
        hitsTxt.setText(Integer.toString(hits));

        missesTxt = (TextView)findViewById(R.id.txtMisses);
        missesTxt.setText(Integer.toString(misses));

        hitTntTxt = (TextView)findViewById(R.id.txtAnyTntHit);
        hitTntTxt.setText(hitTNT);

        timeTxt = (TextView)findViewById(R.id.txtTime);
        timeTxt.setText(textTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_over, menu);
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
