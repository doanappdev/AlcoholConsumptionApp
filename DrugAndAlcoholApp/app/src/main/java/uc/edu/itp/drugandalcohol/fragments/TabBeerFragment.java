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
import uc.edu.itp.drugandalcohol.model.Beer;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TabBeerFragment extends Fragment
    implements View.OnClickListener
{
    // TAG is used for debugging and printing to the LogCat
    private static String TAG = "TabBeerFragment";

    Beer beer;

    // declare various layout components so we can attach click event listeners
    // to them.
    ImageView beer1ImgView, beer2ImgView, beer3ImgView, beer4ImgView;
    TextView beer1InputTxtView, beer2InputTxtView, beer3InputTxtView, beer4InputTxtView;
    TextView beer1ATxtView, beer2ATxtView, beer3ATxtView, beer4ATxtView;
    TextView beer1BTxtView, beer2BTxtView, beer3BTxtView, beer4BTxtView;

    public TabBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // create view object so we can access various layout components
        // such as buttons etc..
        View view  = inflater.inflate(R.layout.fragment_tab_beer, container, false);

        // pass getActivity() as context reference to AlcoholType which
        // creates a SharedPreference object to save drinks consumed
        beer = new Beer(getActivity());

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
        // views from beer row 1
        beer1ImgView = (ImageView)v.findViewById(R.id.imgViewBeer1);
        beer1ATxtView = (TextView)v.findViewById(R.id.txtViewBeer1A);
        beer1BTxtView = (TextView)v.findViewById(R.id.txtViewBeer1B);
        beer1InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput1);

        // views from beer row 2
        beer2ImgView = (ImageView)v.findViewById(R.id.imgViewBeer2);
        beer2ATxtView = (TextView)v.findViewById(R.id.txtViewBeer2A);
        beer2BTxtView = (TextView)v.findViewById(R.id.txtViewBeer2B);
        beer2InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput2);

        // views from beer row 3
        beer3ImgView = (ImageView)v.findViewById(R.id.imgViewBeer3);
        beer3ATxtView = (TextView)v.findViewById(R.id.txtViewBeer3A);
        beer3BTxtView = (TextView)v.findViewById(R.id.txtViewBeer3B);
        beer3InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput3);

        // view from beer row 4
        beer4ImgView = (ImageView)v.findViewById(R.id.imgViewBeer4);
        beer4ATxtView = (TextView)v.findViewById(R.id.txtViewBeer4A);
        beer4BTxtView = (TextView)v.findViewById(R.id.txtViewBeer4B);
        beer4InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput4);

    }

    /*
     * Set click listeners for each layout component in the beer tab.
     * the parameter 'this' refers to the onClick() which must be declared
     * when we implement View.OnClickListener()
     */
    void setClickListeners()
    {
        beer1ImgView.setOnClickListener(this);
        beer2ImgView.setOnClickListener(this);
        beer3ImgView.setOnClickListener(this);
        beer4ImgView.setOnClickListener(this);

        beer1ATxtView.setOnClickListener(this);
        beer1BTxtView.setOnClickListener(this);
        beer2ATxtView.setOnClickListener(this);
        beer2BTxtView.setOnClickListener(this);
        beer3ATxtView.setOnClickListener(this);
        beer3BTxtView.setOnClickListener(this);
        beer4ATxtView.setOnClickListener(this);
        beer4BTxtView.setOnClickListener(this);

        beer1InputTxtView.setOnClickListener(this);
        beer2InputTxtView.setOnClickListener(this);
        beer3InputTxtView.setOnClickListener(this);
        beer4InputTxtView.setOnClickListener(this);
    }

    /*
     * onClick() displays the NumberPad dialog when a view component is clicked on.
     * the parameter passed in showNumberPad() is the requestCode used in onActivityResult()
     */
    public void onClick(View v)
    {
        switch(v.getId()) {
            // display NumberPad when beer row 1 clicked
            case R.id.imgViewBeer1:
            case R.id.txtViewBeer1A:
            case R.id.txtViewBeer1B:
            case R.id.txtViewBeerInput1:
                showNumberPad(Beer.BEER_ROW_1_CLICKED);
                break;

            // beer row 2 clicked
            case R.id.imgViewBeer2:
            case R.id.txtViewBeer2A:
            case R.id.txtViewBeer2B:
            case R.id.txtViewBeerInput2:
                showNumberPad(Beer.BEER_ROW_2_CLICKED);
                break;

            // beer row 3 clicked
            case R.id.imgViewBeer3:
            case R.id.txtViewBeer3A:
            case R.id.txtViewBeer3B:
            case R.id.txtViewBeerInput3:
                showNumberPad(Beer.BEER_ROW_3_CLICKED);
                break;

            // beer row 4 clicked
            case R.id.imgViewBeer4:
            case R.id.txtViewBeer4A:
            case R.id.txtViewBeer4B:
            case R.id.txtViewBeerInput4:
                showNumberPad(Beer.BEER_ROW_4_CLICKED);
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
                case Beer.BEER_ROW_1_CLICKED:
                    // assign value from number pad to text view
                    beer1InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Beer.BEER_ROW_2_CLICKED:
                    beer2InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Beer.BEER_ROW_3_CLICKED:
                    beer3InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Beer.BEER_ROW_4_CLICKED:
                    beer4InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

            }

            // verify the value returned from NumberPad
            Log.i(TAG, "Value clicked on NumberPad = " + standardDrinks);
        }

        // save number of drinks consumed for each input text view,
        // need to convert value to integer before storing value
        beer.setTotalDrinksConsumed(Integer.parseInt(beer1InputTxtView.getText().toString()),
                Integer.parseInt(beer2InputTxtView.getText().toString()),
                Integer.parseInt(beer3InputTxtView.getText().toString()),
                Integer.parseInt(beer4InputTxtView.getText().toString()));

    }
}
