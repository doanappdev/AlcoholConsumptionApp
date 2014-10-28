package uc.edu.itp.drugandalcohol.fragments;



import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DisplayBACDialogFragment extends DialogFragment
    implements View.OnClickListener
{
    SharedPreferences drinkingSharedPrefs;

    TextView displayBACTxtView;
    Button btnOk;

    float bacValue;


    public DisplayBACDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get the layout inflater
        //LayoutInflater inflater = getActivity().getLayoutInflater();

        //builder.setView(inflater.inflate(R.layout.display_bac_dialog, null));

        /* to create an alert dialog with icon, title, and buttons use the code below */
                // Set Dialog Icon
                //.setIcon(R.drawable.icon_name)
                // Set Dialog Title
                //.setTitle("Blood Alcohol Content")
                // Set Dialog Message
                //.setMessage("message")
                // Positive button
                 /*
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something else
                    }
                });

                        // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something else
                    }
                });
                */

        //return builder.create();


        // This code is used to
        // display content from xml file
        final RelativeLayout rLayout = new RelativeLayout(getActivity());
        rLayout.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        // create a full screen dialog with transparency
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rLayout);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.argb(180, 125, 125, 125)));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.display_bac_dialog, container, false);

        btnOk = (Button)view.findViewById(R.id.btnDialogOK);
        btnOk.setOnClickListener(this);

        // get value from shared preferences
        drinkingSharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);
        // get calculated BAC from shared preferences, if no value stored assign default value
        // of 0.00
        bacValue = drinkingSharedPrefs.getFloat(getString(R.string.drinking_current_bac_key), 0.00f);
        String s = String.format("%.2f", bacValue);

        displayBACTxtView = (TextView)view.findViewById(R.id.txtViewDisplayBAC);
        // display BAC in text view
        //DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //displayBACTxtView.setText(Float.toString(bacValue));
        displayBACTxtView.setText(s);

        //Log.d("DisplayBACDialogFragment", Float.toString(bacValue));
        Log.d("DisplayBACDialogFragment", s);

        // return inflated layout as view for fragment
        return view;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnDialogOK:
                // close the dialog
                dismiss();
                break;



        }

    }


}
