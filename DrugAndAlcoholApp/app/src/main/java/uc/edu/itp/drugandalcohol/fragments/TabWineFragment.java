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
import uc.edu.itp.drugandalcohol.model.Wine;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TabWineFragment extends Fragment
    implements View.OnClickListener
{
    // TAG is used for debugging and printing to the LogCat
    private static String TAG = "TabWineFragment";

    Wine wine;

    ImageView wine1ImgView, wine2ImgView, wine3ImgView, wine4ImgView;
    TextView wine1ATxtView, wine2ATxtView, wine3ATxtView, wine4ATxtView;
    TextView wine1BTxtView, wine2BTxtView, wine3BTxtView, wine4BTxtView;
    TextView wine1InputTxtView, wine2InputTxtView, wine3InputTxtView, wine4InputTxtView;

    public TabWineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // create view object so we can access various layout components
        // such as buttons etc..
        View view  = inflater.inflate(R.layout.fragment_tab_wine, container, false);

        // pass getActivity() as context reference as parameter in constructor
        wine = new Wine(getActivity());

        // assign layout from XML to component & attach click listener
        attachViewToId(view);
        setClickListeners();

        // return the layout for this fragment
        return view;
    }

    /*
    * A view in android can be a Layout view such as LinearLayout, RelativeLayout
    * or it can be a layout component such as TextView, Button, EditText etc..
    * the ID for these views can be found in the fragment_tab_beer.xml file
    */
    public void attachViewToId(View v)
    {
        // views from wine row 1
        wine1ImgView = (ImageView)v.findViewById(R.id.imgViewWine1);
        wine1ATxtView = (TextView)v.findViewById(R.id.txtViewWine1A);
        wine1BTxtView = (TextView)v.findViewById(R.id.txtViewWine1B);
        wine1InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput1);

        wine2ImgView = (ImageView)v.findViewById(R.id.imgViewWine2);
        wine2ATxtView = (TextView)v.findViewById(R.id.txtViewWine2A);
        wine2BTxtView = (TextView)v.findViewById(R.id.txtViewWine2B);
        wine2InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput2);

        wine3ImgView = (ImageView)v.findViewById(R.id.imgViewWine3);
        wine3ATxtView = (TextView)v.findViewById(R.id.txtViewWine3A);
        wine3BTxtView = (TextView)v.findViewById(R.id.txtViewWine3B);
        wine3InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput3);

        wine4ImgView = (ImageView)v.findViewById(R.id.imgViewWine4);
        wine4ATxtView = (TextView)v.findViewById(R.id.txtViewWine4A);
        wine4BTxtView = (TextView)v.findViewById(R.id.txtViewWine4B);
        wine4InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput4);

    }

    /*
     * Set click listeners for each layout component in the beer tab.
     * the parameter 'this' refers to the onClick() which must be declared
     * when we implement View.OnClickListener()
     */
    void setClickListeners()
    {
        wine1ImgView.setOnClickListener(this);
        wine2ImgView.setOnClickListener(this);
        wine3ImgView.setOnClickListener(this);
        wine4ImgView.setOnClickListener(this);

        wine1ATxtView.setOnClickListener(this);
        wine1BTxtView.setOnClickListener(this);
        wine2ATxtView.setOnClickListener(this);
        wine2BTxtView.setOnClickListener(this);
        wine3ATxtView.setOnClickListener(this);
        wine3BTxtView.setOnClickListener(this);
        wine4ATxtView.setOnClickListener(this);
        wine4BTxtView.setOnClickListener(this);

        wine1InputTxtView.setOnClickListener(this);
        wine2InputTxtView.setOnClickListener(this);
        wine3InputTxtView.setOnClickListener(this);
        wine4InputTxtView.setOnClickListener(this);

    }

    /*
     * onClick() displays the NumberPad dialog when a view component is clicked on.
     * the parameter passed in showNumberPad() is the requestCode used in onActivityResult()
     */
    public void onClick(View v)
    {
        switch(v.getId()) {
            // display NumberPad when wine row 1 clicked
            case R.id.imgViewSpirits1:
            case R.id.txtViewWine1A:
            case R.id.txtViewWine1B:
            case R.id.txtViewWineInput1:
                showNumberPad(Wine.WINE_ROW_1_CLICKED);
                break;

            // wine row 2 clicked
            case R.id.imgViewSpirits2:
            case R.id.txtViewWine2A:
            case R.id.txtViewWine2B:
            case R.id.txtViewWineInput2:
                showNumberPad(Wine.WINE_ROW_2_CLICKED);
                break;

            // wine row 3 clicked
            case R.id.imgViewSpirits3:
            case R.id.txtViewWine3A:
            case R.id.txtViewWine3B:
            case R.id.txtViewWineInput3:
                showNumberPad(Wine.WINE_ROW_3_CLICKED);
                break;

            // wine row 4 clicked
            case R.id.imgViewSpirits4:
            case R.id.txtViewWine4A:
            case R.id.txtViewWine4B:
            case R.id.txtViewWineInput4:
                showNumberPad(Wine.WINE_ROW_4_CLICKED);
                break;
        }
    }

    void showNumberPad(int viewId)
    {
        // use FragmentManager to display the Number pad, we setTargetFragment()
        // with the viewId as one of the parameters so the number pad returns the
        // value entered in by the user to the correct text view
        FragmentManager fm = getFragmentManager();
        NumberPadFragment numberPadFragment = new NumberPadFragment();
        numberPadFragment.setTargetFragment(this, viewId);
        numberPadFragment.show(fm, "Alcohol Consumption");
    }

    /*
     * this method is called when the NumberPad Dialog closes
     * It creates a Bundle object which stores the number selected
     * by user and returns that number to the calling fragment
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
                case Wine.WINE_ROW_1_CLICKED:
                    // assign value from number pad to text view
                    wine1InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Wine.WINE_ROW_2_CLICKED:
                    wine2InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Wine.WINE_ROW_3_CLICKED:
                    wine3InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case Wine.WINE_ROW_4_CLICKED:
                    wine4InputTxtView.setText(Integer.toString(standardDrinks));
                    break;
            }

            // verify the value returned from NumberPad
            Log.i(TAG, "Value clicked on NumberPad = " + standardDrinks);
        }

        // save number of drinks consumed for each input text view,
        // need to convert value to integer before storing value
        wine.setTotalDrinksConsumed(Integer.parseInt(wine1InputTxtView.getText().toString()),
                Integer.parseInt(wine2InputTxtView.getText().toString()),
                Integer.parseInt(wine3InputTxtView.getText().toString()),
                Integer.parseInt(wine4InputTxtView.getText().toString()));

    }

    /*******************************************************************************
     * Testing methods
     *
     ******************************************************************************/


    public String getWineTabStringTest()
    {
        return "Testing wine tab";
    }


}
