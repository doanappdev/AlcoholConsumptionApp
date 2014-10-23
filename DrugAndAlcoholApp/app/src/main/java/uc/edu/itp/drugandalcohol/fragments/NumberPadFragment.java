package uc.edu.itp.drugandalcohol.fragments;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NumberPadFragment extends DialogFragment
    implements View.OnClickListener
{
    Button num0Btn, num1Btn, num2Btn, num3Btn, num4Btn, num5Btn, num6Btn, num7Btn, num8Btn,
            num9Btn, okBtn, clearBtn, exitBtn;

    TextView displayTxtView;

    //NumberPadListener numberPadListener;

    // interface to help communicate with the calling fragment
    //public interface NumberPadListener
    //{
    //    public void message(String data);
    //}

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

         /*
        // create a AlertDialog
        // use the builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.eula_agreement)
               .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id)
                   {
                       // close the dialog
                       dismiss();
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // close the dialog
                       dismiss();
                   }
               });

        // return the alert dialog object
        return builder.create();
        */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_number_pad, container, false);

        displayTxtView = (TextView)view.findViewById(R.id.txtViewDisplayNumber);

        num0Btn = (Button)view.findViewById(R.id.btnNum0);
        num1Btn = (Button)view.findViewById(R.id.btnNum1);
        num2Btn = (Button)view.findViewById(R.id.btnNum2);
        num3Btn = (Button)view.findViewById(R.id.btnNum3);
        num4Btn = (Button)view.findViewById(R.id.btnNum4);
        num5Btn = (Button)view.findViewById(R.id.btnNum5);
        num6Btn = (Button)view.findViewById(R.id.btnNum6);
        num7Btn = (Button)view.findViewById(R.id.btnNum7);
        num8Btn = (Button)view.findViewById(R.id.btnNum8);
        num9Btn = (Button)view.findViewById(R.id.btnNum9);
        clearBtn = (Button)view.findViewById(R.id.btnClear);
        exitBtn = (Button)view.findViewById(R.id.btnExit);
        okBtn = (Button)view.findViewById(R.id.btnOK);

        // setting onclick listener for buttons
        okBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        num0Btn.setOnClickListener(this);
        num1Btn.setOnClickListener(this);
        num2Btn.setOnClickListener(this);
        num3Btn.setOnClickListener(this);
        num4Btn.setOnClickListener(this);
        num5Btn.setOnClickListener(this);
        num6Btn.setOnClickListener(this);
        num7Btn.setOnClickListener(this);
        num8Btn.setOnClickListener(this);
        num9Btn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    /*
        override the onAttach method to help pass info between number pad fragment
        and calculate BAC fragment

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        numberPadListener = (NumberPadListener)getActivity();

        numberPadListener.message("55");

        //if(activity instanceof NumberPadListener)
        //{
        //    numberPadListener = (NumberPadListener)getTargetFragment();
        //}
        //else
        //{
        //    throw new ClassCastException(activity.toString()
        //            + " must implement NumberPadFragment.numberPadListener");
        //}
    }
     */
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnNum0:
                displayTxtView.setText("0");
                break;
            case R.id.btnNum1:
                displayTxtView.setText("1");
                break;
            case R.id.btnNum2:
                displayTxtView.setText("2");
                break;
            case R.id.btnNum3:
                displayTxtView.setText("3");
                break;
            case R.id.btnNum4:
                displayTxtView.setText("4");
                break;
            case R.id.btnNum5:
                displayTxtView.setText("5");
                break;
            case R.id.btnNum6:
                displayTxtView.setText("6");
                break;
            case R.id.btnNum7:
                displayTxtView.setText("7");
                break;
            case R.id.btnNum8:
                displayTxtView.setText("8");
                break;
            case R.id.btnNum9:
                displayTxtView.setText("9");
                break;
            case R.id.btnOK:
                returnNumber();
                dismiss();
                //numberPadListener.message("This string was passed back to fragment");
                break;
            case R.id.btnExit:
                dismiss();
                break;

        }
    }

    // use Bundle to pass data between dialog fragment and calculate BAC fragment
    public void returnNumber()
    {
        // declare intent and bundle
        Intent i = new Intent();
        Bundle extras = new Bundle();

        // get number from text view
        int numOfDrinks = Integer.parseInt(displayTxtView.getText().toString());

        // save integer value to bundle
        extras.putInt("NumOfDrinks", numOfDrinks);
        i.putExtras(extras);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);

    }



}
