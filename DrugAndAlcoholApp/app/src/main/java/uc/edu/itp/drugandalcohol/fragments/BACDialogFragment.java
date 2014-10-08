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
 * A simple {@link Fragment} subclass.
 *
 */
public class BACDialogFragment extends DialogFragment {


    public BACDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                //.setIcon(R.drawable.iconname)
                // Set Dialog Title
                .setTitle("Blood Alcohol Content")
                        // Set Dialog Message
                .setMessage("Your current BAC is")

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


    /*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bacdialog, container, false);
    }

    */
}
