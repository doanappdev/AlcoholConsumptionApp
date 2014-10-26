package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

public class HighScoreActivity extends Activity {

    TextView highScoreTxt;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScoreTxt = (TextView)findViewById(R.id.txtHighScore);
        highScoreTxt.setText(
            "1 - 733t m4n: OVER 9000!!!\n" +
            "2 - 2nd b35t: just under 9000... :(\n" +
            "3 - m1d773 ch17d: 7777\n" +
            "4 - Forest Gump: 500\n" +
            "5 - w0r5t p74y4 3v4: -1000000"
        );

        backBtn = (Button)findViewById(R.id.btnBack);
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
