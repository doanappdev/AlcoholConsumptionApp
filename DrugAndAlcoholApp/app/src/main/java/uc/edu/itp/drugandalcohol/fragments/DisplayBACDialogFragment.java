package uc.edu.itp.drugandalcohol.fragments;



import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DisplayBACDialogFragment extends DialogFragment {


    public DisplayBACDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.display_bac_dialog, null));

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

        return builder.create();
    }


    /*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.display_bac_dialog, container, false);
    }

    */
}
