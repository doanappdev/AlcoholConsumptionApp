package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

/*
*
* TO DO: IMPLEMENT INSTURCTIONS MENU STRING AND IMAGES
*
*/

public class InstructionsActivity extends Activity {

    int index;
    String[] instructions;

    TextView instructionsTxt;
    TextView pageNoTxt;
    Button nextBtn;
    Button prevBtn;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Resources res = getResources();

        //Sets the 1st page of instructions
        index = 0;
        instructions = res.getStringArray(R.array.instructions);

        //sets the text of 1st page onto the screen
        instructionsTxt = (TextView)findViewById(R.id.txtInstructions);
        instructionsTxt.setText(instructions[index]);

        //sets page number on screen
        pageNoTxt = (TextView)findViewById(R.id.txtPageNo);
        pageNoTxt.setText(instructions[index]);

        //implements next instructions button
        nextBtn = (Button)findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make sure index does not go less than 0 or more
                //than total instruction pages minus 1
                if(++index >= instructions.length) index = 0;
                //Sets current page of instructions
                instructionsTxt.setText(instructions[index]);
                //Page number is 1 more than index,
                //because arrays start at 0
                pageNoTxt.setText("Page - " + String.valueOf(index+1));
            }
        });

        //implements previous instructions button
        prevBtn = (Button)findViewById(R.id.btnPrev);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make sure index does not go less than 0 or more
                //than total instruction pages minus 1
                if(--index < 0) index = instructions.length - 1;
                //Sets current page of instructions
                instructionsTxt.setText(instructions[index]);
                //Page number is 1 more than index,
                //because arrays start at 0
                pageNoTxt.setText("Page - " + String.valueOf(index+1));
            }
        });

        //Implements exiting out of instructions button
        backBtn = (Button)findViewById(R.id.btnExitInstructions);
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
