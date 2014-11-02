package uc.edu.itp.drugandalcohol.fragments.dialogs;



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

import java.util.Random;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.AlcoholType;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BACDialogFragment extends DialogFragment
    implements View.OnClickListener
{
    SharedPreferences drinkingSharedPrefs;

    TextView displayBACTxtView, displayTipsQuotes;
    Button btnOk;

    Random rand;
    float bacValue;


    public BACDialogFragment() {
        // Required empty public constructor
    }

    /*
     * this method defines the type of dialog that is create
     * is we leave this out a default alert dialog will display
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // This code is used to display content from xml file
        final RelativeLayout rLayout = new RelativeLayout(getActivity());
        rLayout.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        // create a full screen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rLayout);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.argb(180, 125, 125, 125)));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;

    }

    /*
     * this method can be used to define the layout for the dialog
     * we use the layout file display_bac_dialog
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.display_bac_dialog, container, false);
        // instantiate random object
        rand = new Random();

        displayBACTxtView = (TextView)view.findViewById(R.id.txtViewDisplayBAC);
        displayTipsQuotes = (TextView)view.findViewById(R.id.txtViewTipsQuotes);

        btnOk = (Button)view.findViewById(R.id.btnDialogOK);
        btnOk.setOnClickListener(this);

        displayBACValue();

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

    public void displayBACValue()
    {
        // generate random number between 0-12
        int randMsgPos = rand.nextInt(13);

        // get string array values from strings.xml file and store values
        // in tipsAndQuotes array
        String[] tipsAndQuotes = getResources().getStringArray(R.array.tips_quotes);

        // get BAC value from shared preferences
        drinkingSharedPrefs = getActivity().getSharedPreferences(
                AlcoholType.DRINK_PREF_FILE_NAME, Context.MODE_PRIVATE);

        // get calculated BAC from shared preferences, if no value stored
        // assign default value of 0.00
        bacValue = drinkingSharedPrefs.getFloat(getString(R.string.drinking_current_bac_key), 0.00f);
        // set bacValue to 2 decimal places
        String s = String.format("%.2f", bacValue);

        // display BAC in text view
        displayBACTxtView.setText(s);
        // display random tip or quote
        displayTipsQuotes.setText(tipsAndQuotes[randMsgPos]);

        // check value of 's' for debugging
        Log.d("DisplayBACDialogFragment", s);
    }

    /*******************************************************************************
     * Testing methods
     *
     ******************************************************************************/

}
