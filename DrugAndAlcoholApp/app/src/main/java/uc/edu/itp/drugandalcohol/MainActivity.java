package uc.edu.itp.drugandalcohol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import uc.edu.itp.drugandalcohol.view.CalculateBACActivity;
import uc.edu.itp.drugandalcohol.view.EmergencyActivity;
import uc.edu.itp.drugandalcohol.view.GameMenuActivity;
import uc.edu.itp.drugandalcohol.view.MapActivity;
import uc.edu.itp.drugandalcohol.view.UserDetailsActivity;


public class MainActivity extends Activity
{
    // TAG is identifier when printing to the log cat for
    // debugging/testing purposes
    private final static String TAG = "MainActivity";
    private final static String EULA_FILE_NAME = "Eula_Shared_Prefs";
    private static boolean EULA_ACCEPTED = false;

    TextView heading1TxtView;
    ImageButton detailsImgBtn, calculateImgBtn, emergencyImgBtn,
            gameMenuImgBtn, proximityImgBtn, deleteDataImgBtn;

    Random rand;
    int randNum;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // attach image buttons to ID's
        detailsImgBtn = (ImageButton)findViewById(R.id.imgBtnEnterDetails);
        calculateImgBtn = (ImageButton)findViewById(R.id.imgBtnCalculateBAC);
        emergencyImgBtn = (ImageButton)findViewById(R.id.imgBtnEmergencySMS);
        gameMenuImgBtn = (ImageButton)findViewById(R.id.imgBtnReactionTest);
        proximityImgBtn = (ImageButton)findViewById(R.id.imgBtnProximity);
        deleteDataImgBtn = (ImageButton)findViewById(R.id.imgBtnDeleteData);
        heading1TxtView = (TextView)findViewById(R.id.txtViewMainH1);

        // setting the font for text view
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto-Bold.ttf");
        heading1TxtView.setTypeface(font);



        // EULA_ACCEPTED when user clicks accept on
        // EULA dialog
        if(!EULA_ACCEPTED)
        {
            // display EULA when app is first installed
            displayEULA();
        }
        else
        {
            rand = new Random();
            randNum = rand.nextInt(13); // generate random number between 0-12
            // display random tips or quotes
            displayTipsQuotes(randNum);
        }



        // click event handlers for image buttons
        detailsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userDetailsIntent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                startActivity(userDetailsIntent);
            }
        });

        calculateImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent calculateBACIntent = new Intent(getApplicationContext(), CalculateBACActivity.class);
                startActivity(calculateBACIntent);

            }
        });

        emergencyImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emergencyIntent = new Intent(getApplicationContext(), EmergencyActivity.class);
                startActivity(emergencyIntent);
            }
        });

        gameMenuImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameMenuIntent = new Intent(getApplicationContext(), GameMenuActivity.class);
                startActivity(gameMenuIntent);
            }
        });

        proximityImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proximityIntent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(proximityIntent);
            }
        });

        deleteDataImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               displayConfirmDelete();
            }
        });


    }

    public void displayEULA()
    {
        // create alert dialog to display EULA
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // set boolean value equal to true when user
                        // clicks OK on EULA dialog
                        EULA_ACCEPTED = true;

                        // close dialog
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("DECLINE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dialogInterface.cancel();
                                onStop();
                            }
                        }
                );

        // create layout for dialog
        View childView = getLayoutInflater().inflate(R.layout.fragment_eula, null);
        alertDialog.setView(childView);
        // show dialog
        alertDialog.show();

    }

    /*
     * method creates dialog to display random tip/quote to
     * user.
     */
    public void displayTipsQuotes(int pos)
    {
        // get string array values from strings.xml file
        // and store values in array
        String[] tipsAndQuotes = getResources().getStringArray(R.array.tips_quotes);
        String randomTipQuote = tipsAndQuotes[pos];

        // create alert dialog to display EULA
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // close dialog
                dialogInterface.dismiss();
            }
            })
            .setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //dialogInterface.cancel();
                            onStop();
                        }
                    }
            );

        // create layout for dialog
        View view = getLayoutInflater().inflate(R.layout.fragment_tips_quotes_dialog, null);

        TextView tipsQuotesTxtView = (TextView)view.findViewById(R.id.txtViewTipsQuotes);
        tipsQuotesTxtView.setText(randomTipQuote);

        alertDialog.setView(view);
        // show dialog
        alertDialog.show();
    }

    public void displayConfirmDelete()
    {
        // create alert dialog to display confirm delete
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // TODO add code to delete data
                // close dialog
                dialogInterface.dismiss();
            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // close dialog and app
                                onStop();
                            }
                        }
                );

        // create layout for dialog
        View childView = getLayoutInflater().inflate(R.layout.fragment_confirm_delete_dialog, null);
        alertDialog.setView(childView);
        // show dialog
        alertDialog.show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        super.onStop();


        /*
            Todo: add code to save phone state and data
        */
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
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
    */
    /*******************************************************************************
     * Testing methods
     *
     ******************************************************************************/
    public boolean testDetailsButton()
    {
        return detailsImgBtn.performClick();
    }

    public boolean testCalculateButton()
    {
        return detailsImgBtn.performClick();
    }

    public boolean testEmergencyButton()
    {
        return detailsImgBtn.performClick();
    }

    public boolean testGameButton()
    {
        return detailsImgBtn.performClick();
    }

    public boolean testProximityButton()
    {
        return detailsImgBtn.performClick();
    }

    public boolean testDeleteButton()
    {
        return detailsImgBtn.performClick();
    }

}
