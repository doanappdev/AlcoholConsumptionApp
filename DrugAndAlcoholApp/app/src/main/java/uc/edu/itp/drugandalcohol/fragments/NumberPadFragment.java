package uc.edu.itp.drugandalcohol.fragments;



import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NumberPadFragment extends DialogFragment
{
    /*
	 * newInstance() method allows a new instance of the fragment to be created,
	 * and at the same time it accepts an argument specifying the string(title)
	 * to display in the alert dialog. The title is then stored in a Bundle object
	 * for use later.
	 */
    public static NumberPadFragment newInstance(String title)
    {
        NumberPadFragment numberPad = new NumberPadFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        numberPad.setArguments(args);
        return numberPad;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
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
                new ColorDrawable(Color.argb(120, 125, 125, 125)));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number_pad, container, false);
    }


}
