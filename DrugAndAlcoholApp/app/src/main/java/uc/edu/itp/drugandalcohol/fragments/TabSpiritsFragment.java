package uc.edu.itp.drugandalcohol.fragments;



import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.AlcoholType;
import uc.edu.itp.drugandalcohol.model.Spirits;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TabSpiritsFragment extends Fragment
    implements View.OnClickListener
{
    // TAG is used for debugging and printing to the LogCat
    private static String TAG = "TabSpiritsFragment";

    Spirits spirits;

    ImageView spirits1ImgView, spirits2ImgView, spirits3ImgView, spirits4ImgView;
    TextView spirits1ATxtView, spirits2ATxtView, spirits3ATxtView, spirits4ATxtView;
    TextView spirits1BTxtView, spirits2BTxtView, spirits3BTxtView, spirits4BTxtView;
    TextView spirits1InputTxtView, spirits2InputTxtView, spirits3InputTxtView, spirits4InputTxtView;

    public TabSpiritsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // create view object so we can access various layout components
        // such as buttons etc..
        View view  = inflater.inflate(R.layout.fragment_tab_spirits, container, false);

        // pass getActivity() as context reference to AlcoholType which
        // creates a SharedPreference object to save drinks consumed
        spirits = new Spirits(getActivity());

        // assign layout from XML to component & attach click listener
        attachViewToId(view);
        setClickListeners();

        // return inflated the layout for this fragment
        return view;

    }

    /*
     * A view in android can be a Layout view such as LinearLayout, RelativeLayout
     * or it can be a layout component such as TextView, Button, EditText etc..
     * the ID for these views can be found in the fragment_tab_beer.xml file
     */
    public void attachViewToId(View v)
    {
        // views from spirits row 1
        spirits1ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits1);
        spirits1ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits1A);
        spirits1BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits1B);
        spirits1InputTxtView = (TextView) v.findViewById(R.id.txtViewSpiritsInput1);

        // view from spirits row 2
        spirits2ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits2);
        spirits2ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits2A);
        spirits2BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits2B);
        spirits2InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput2);

        // view from spirits row 3
        spirits3ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits3);
        spirits3ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits3A);
        spirits3BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits3B);
        spirits3InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput3);

        // view from spirits row 4
        spirits4ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits4);
        spirits4ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits4A);
        spirits4BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits4B);
        spirits4InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput4);

    }

    /*
     * Set click listeners for each layout component in the beer tab.
     * the parameter 'this' refers to the onClick() which must be declared
     * when we implement View.OnClickListener()
     */
    void setClickListeners()
    {
        spirits1ImgView.setOnClickListener(this);
        spirits2ImgView.setOnClickListener(this);
        spirits3ImgView.setOnClickListener(this);
        spirits4ImgView.setOnClickListener(this);

        spirits1ATxtView.setOnClickListener(this);
        spirits1BTxtView.setOnClickListener(this);
        spirits2ATxtView.setOnClickListener(this);
        spirits2BTxtView.setOnClickListener(this);
        spirits3ATxtView.setOnClickListener(this);
        spirits3BTxtView.setOnClickListener(this);
        spirits4ATxtView.setOnClickListener(this);
        spirits4BTxtView.setOnClickListener(this);

        spirits1InputTxtView.setOnClickListener(this);
        spirits2InputTxtView.setOnClickListener(this);
        spirits3InputTxtView.setOnClickListener(this);
        spirits4InputTxtView.setOnClickListener(this);


    }

    /*
     * onClick() displays the NumberPad dialog when a view component is clicked on.
     * the parameter passed in showNumberPad() is the requestCode used in onActivityResult()
     */
    public void onClick(View v)
    {
        switch(v.getId())
        {
            // spirits row 1 clicked
            case R.id.imgViewSpirits1:
            case R.id.txtViewSpirits1A:
            case R.id.txtViewSpirits1B:
            case R.id.txtViewSpiritsInput1:
                showNumberPad(Spirits.SPIRIT_ROW_ONE_CLICKED);
                break;

            // spirits row 2 clicked
            case R.id.imgViewSpirits2:
            case R.id.txtViewSpirits2A:
            case R.id.txtViewSpirits2B:
            case R.id.txtViewSpiritsInput2:
                showNumberPad(Spirits.SPIRIT_ROW_TWO_CLICKED);
                break;

            // spirits row 3 clicked
            case R.id.imgViewSpirits3:
            case R.id.txtViewSpirits3A:
            case R.id.txtViewSpirits3B:
            case R.id.txtViewSpiritsInput3:
                showNumberPad(Spirits.SPIRIT_ROW_THREE_CLICKED);
                break;

            // spirits row 4 clicked
            case R.id.imgViewSpirits4:
            case R.id.txtViewSpirits4A:
            case R.id.txtViewSpirits4B:
            case R.id.txtViewSpiritsInput4:
                showNumberPad(Spirits.SPIRIT_ROW_FOUR_CLICKED);
                break;
        }
    }

    void showNumberPad(int viewId)
    {
        // use FragmentManager to display the number pad, we setTargetFragment()
        // with the viewId as one of the parameters so the number pad returns the
        // value entered in by the user to the correct input text view
        FragmentManager fm = getFragmentManager();
        NumberPadFragment numberPadFragment = new NumberPadFragment();
        numberPadFragment.setTargetFragment(this, viewId);
        numberPadFragment.show(fm, "Alcohol Consumption");
    }

    /*
     * this method is called when the NumberPad Dialog closes
     * It creates a Bundle object which
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bundle bundle = data.getExtras();

        int standardDrinks = bundle.getInt("NumOfDrinks");

        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case Spirits.SPIRIT_ROW_ONE_CLICKED:
                    // assign value from number pad to text view
                    spirits1InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Spirits.SPIRIT_ROW_TWO_CLICKED:
                    spirits2InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Spirits.SPIRIT_ROW_THREE_CLICKED:
                    spirits3InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Spirits.SPIRIT_ROW_FOUR_CLICKED:
                    spirits4InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

            }

            // verify the value returned from NumberPad
            Log.i(TAG, "Value clicked on NumberPad = " + standardDrinks);
        }

        // save number of drinks consumed for each input text view,
        // need to convert value to integer before storing value
        spirits.setTotalDrinksConsumed(Integer.parseInt(spirits1InputTxtView.getText().toString()),
                Integer.parseInt(spirits2InputTxtView.getText().toString()),
                Integer.parseInt(spirits3InputTxtView.getText().toString()),
                Integer.parseInt(spirits4InputTxtView.getText().toString()));


    }


}
