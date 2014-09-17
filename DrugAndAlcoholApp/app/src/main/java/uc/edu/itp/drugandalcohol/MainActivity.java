package uc.edu.itp.drugandalcohol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import uc.edu.itp.drugandalcohol.fragments.EULAFragment;
import uc.edu.itp.drugandalcohol.view.ConsumptionActivity;


public class MainActivity extends Activity
{
    Button consumptionBtn, detailsBtn, exitBtn;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consumptionBtn = (Button)findViewById(R.id.btnConsumption);
        consumptionBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent consumptionIntent = new Intent(getApplicationContext(), ConsumptionActivity.class);
                startActivity(consumptionIntent);
            }
        });



    }

    @Override
    protected void onStart()
    {
        super.onStart();

        /*
         * code for a dialog which is slightly different to
         * AlertDialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_eula);
        //dialog.setTitle("EULA");

        // to set background color for the dialog fragment set values for argb(alpha, red, green, blue)
        // 0 has less color while 255 has more color for rbg colors.
        // for alpha 0 is transparent and 0 has no less transparency.
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 47, 53, 71)));

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.fragment_bg));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button = (Button) dialog.findViewById(R.id.btnOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
            }
        });

        Button cancelBtn = (Button) dialog.findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // ToDo: add code to handle the cancel event to exit app
                dialog.dismiss();
            }
        });
        */

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })
        .setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //dialogInterface.cancel();
                onStop();
            }
        });

        View childView = getLayoutInflater().inflate(R.layout.fragment_eula, null);
        alertDialog.setView(childView);

        final AlertDialog dialog = alertDialog.create();



        dialog.show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        /*
            Todo: add code to save phone state and data
         */
    }

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
}
