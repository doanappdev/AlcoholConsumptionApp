package uc.edu.itp.drugandalcohol.fragments;



import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uc.edu.itp.drugandalcohol.R;

/**
 * Dialog fragment to represent the EULA,
 * need to decide if we are going to use this fragment
 * for the EULA??
 *
 *
 */
public class EULADialogFragment extends DialogFragment
{


    public EULADialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                //.setIcon(R.drawable.iconname)
                        // Set Dialog Title
                .setTitle("Alert DialogFragment")
                        // Set Dialog Message
                .setMessage("Alert DialogFragment Tutorial")

                        // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                    }
                })

                        // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        // Do something else
                    }
                }).create();
    }





}
