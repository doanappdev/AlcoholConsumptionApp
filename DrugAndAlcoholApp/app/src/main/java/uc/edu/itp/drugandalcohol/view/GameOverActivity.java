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
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

public class GameOverActivity extends Activity {

    int score;
    int hits;
    int misses;
    String hitTNT;
    String textTime;

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

        Intent intent = getIntent();
        score = intent.getIntExtra("score", -1);
        hits = intent.getIntExtra("hits", -1);
        misses = intent.getIntExtra("misses", -1);
        textTime = intent.getStringExtra("textTime");
        hitTNT = intent.getStringExtra("hitTNT");

        setResultsText();

        highScoreBtn = (Button)findViewById(R.id.btnGameHighScore);
        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent highScoreIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(highScoreIntent);
            }
        });

        backBtn = (Button)findViewById(R.id.btnBackMainMenu);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setResultsText(){
        scoreTxt = (TextView)findViewById(R.id.txtScore);
        scoreTxt.setText(score);

        hitsTxt = (TextView)findViewById(R.id.txtHits);
        hitsTxt.setText(hits);

        missesTxt = (TextView)findViewById(R.id.txtMisses);
        missesTxt.setText(misses);

        hitTntTxt = (TextView)findViewById(R.id.txtHitTNT);
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
