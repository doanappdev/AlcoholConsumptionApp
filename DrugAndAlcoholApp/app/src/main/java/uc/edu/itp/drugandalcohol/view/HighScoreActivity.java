package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

//This is just a dummy class. No implementations of
//this class has been made yet.

/*
*
* TO DO: IMPLEMENT HIGH SCORE MENU FUNCTIONS
*
*/

public class HighScoreActivity extends Activity {

    TextView no1Txt;
    TextView no2Txt;
    TextView no3Txt;
    TextView no4Txt;
    TextView no5Txt;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        no1Txt = (TextView)findViewById(R.id.txtNo1);
        no1Txt.setText("OVER 9000!!!");
        no2Txt = (TextView)findViewById(R.id.txtNo2);
        no2Txt.setText("just under 9000... :(");
        no3Txt = (TextView)findViewById(R.id.txtNo3);
        no3Txt.setText("7777");
        no4Txt = (TextView)findViewById(R.id.txtNo4);
        no4Txt.setText("1'm Gump.");
        no5Txt = (TextView)findViewById(R.id.txtNo5);
        no5Txt.setText("-3.141592653... etc.");

        backBtn = (Button)findViewById(R.id.btnExitHighScore);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.high_score, menu);
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
