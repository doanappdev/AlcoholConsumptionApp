package uc.edu.itp.drugandalcohol.fragments;



import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.controller.SimpleTabDefinition;
import uc.edu.itp.drugandalcohol.controller.TabDefinition;
import uc.edu.itp.drugandalcohol.model.AlcoholType;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalculateBACTopFragment extends Fragment
        implements TabHost.OnTabChangeListener, View.OnTouchListener, View.OnClickListener
{
    /*
    // set text for tab heading, pass the details for each tab using array
    // of TabDefinitions
    private final TabDefinition[] tabDefinitions = new TabDefinition[] {
            new SimpleTabDefinition(R.id.tab1, R.layout.tab_heading,
                    R.string.beer_tab, R.id.tabTitle, new Fragment()),
            new SimpleTabDefinition(R.id.tab2, R.layout.tab_heading,
                    R.string.wine_tab, R.id.tabTitle, new Fragment()),
            new SimpleTabDefinition(R.id.tab3, R.layout.tab_heading,
                    R.string.spirits_tab, R.id.tabTitle, new Fragment()),

    };
    */


    // fields
    private boolean DRINKS_ENTERED_ALREADY = false;

    // values to register which row was clicked
    private static final int BEER_ROW_1_CLICKED = 0;
    private static final int BEER_ROW_2_CLICKED = 1;
    private static final int BEER_ROW_3_CLICKED = 2;
    private static final int BEER_ROW_4_CLICKED = 3;
    private static final int WINE_ROW_1_CLICKED = 4;
    private static final int WINE_ROW_2_CLICKED = 5;
    private static final int WINE_ROW_3_CLICKED = 6;
    private static final int WINE_ROW_4_CLICKED = 7;
    private static final int SPIRIT_ONE_CLICKED = 8;
    private static final int SPIRIT_TWO_CLICKED = 9;
    private static final int SPIRIT_THREE_CLICKED = 10;
    private static final int SPIRIT_FOUR_CLICKED = 11;

    RelativeLayout relativeLayoutBeer;
    View v;
    TabHost _tabHost;

    TextView beer1InputTxtView, beer2InputTxtView, beer3InputTxtView, beer4InputTxtView;
    TextView wine1InputTxtView, wine2InputTxtView, wine3InputTxtView, wine4InputTxtView;
    TextView spirits1InputTxtView, spirits2InputTxtView, spirits3InputTxtView, spirits4InputTxtView;

    TextView beer1ATxtView, beer2ATxtView, beer3ATxtView, beer4ATxtView;
    TextView beer1BTxtView, beer2BTxtView, beer3BTxtView, beer4BTxtView;
    TextView wine1ATxtView, wine2ATxtView, wine3ATxtView, wine4ATxtView;
    TextView wine1BTxtView, wine2BTxtView, wine3BTxtView, wine4BTxtView;
    TextView spirits1ATxtView, spirits2ATxtView, spirits3ATxtView, spirits4ATxtView;
    TextView spirits1BTxtView, spirits2BTxtView, spirits3BTxtView, spirits4BTxtView;

    ImageView beer1ImgView, beer2ImgView, beer3ImgView, beer4ImgView;
    ImageView wine1ImgView, wine2ImgView, wine3ImgView, wine4ImgView;
    ImageView spirits1ImgView, spirits2ImgView, spirits3ImgView, spirits4ImgView;

    SharedPreferences sharedPrefs;
    AlcoholType alcoholType;

    public CalculateBACTopFragment() {
        // Required empty public constructor
    }


    //
    // Public Members
    //
    @Override
    public void onTabChanged(String tabId)
    {
        /*
        for (TabDefinition tab : tabDefinitions)
        {
            if (tabId != tab.getId())
            {
                continue;
            }

            updateTab(tabId, tab.getFragment(), tab.getTabContentViewId());
            return;
        }

        throw new IllegalArgumentException("The specified tab id '" +
                tabId + "' does not exist.");
                */
    }

    //@Override
   // public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    //{
        /*
        // inflate fragment_calculate_bac_top as the fragment view
        v = inflater.inflate(R.layout.fragment_calculate_bac_top, null);

        // initialise the tabhost
        _tabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        _tabHost.setup();

        for (TabDefinition tab : tabDefinitions) {
            _tabHost.addTab(createTab(inflater, _tabHost, v, tab));
        }

        //alcoholType = new AlcoholType();

        // declare image view
        beer1ImgView = (ImageView)v.findViewById(R.id.imgViewBeer1);
        beer2ImgView = (ImageView)v.findViewById(R.id.imgViewBeer2);
        beer3ImgView = (ImageView)v.findViewById(R.id.imgViewBeer3);
        beer4ImgView = (ImageView)v.findViewById(R.id.imgViewBeer4);
        wine1ImgView = (ImageView)v.findViewById(R.id.imgViewWine1);
        wine2ImgView = (ImageView)v.findViewById(R.id.imgViewWine2);
        wine3ImgView = (ImageView)v.findViewById(R.id.imgViewWine3);
        wine4ImgView = (ImageView)v.findViewById(R.id.imgViewWine4);
        spirits1ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits1);
        spirits2ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits2);
        spirits3ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits3);
        spirits4ImgView = (ImageView)v.findViewById(R.id.imgViewSpirits4);

        // set click listener to image view
        beer1ImgView.setOnClickListener(this);
        beer2ImgView.setOnClickListener(this);
        beer3ImgView.setOnClickListener(this);
        beer4ImgView.setOnClickListener(this);
        wine1ImgView.setOnClickListener(this);
        wine2ImgView.setOnClickListener(this);
        wine3ImgView.setOnClickListener(this);
        wine4ImgView.setOnClickListener(this);
        spirits1ImgView.setOnClickListener(this);
        spirits2ImgView.setOnClickListener(this);
        spirits3ImgView.setOnClickListener(this);
        spirits4ImgView.setOnClickListener(this);

        // assign view defined in XML file to input text view
        beer1InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput1);
        beer2InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput2);
        beer3InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput3);
        beer4InputTxtView = (TextView) v.findViewById(R.id.txtViewBeerInput4);
        wine1InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput1);
        wine2InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput2);
        wine3InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput3);
        wine4InputTxtView = (TextView) v.findViewById(R.id.txtViewWineInput4);
        spirits1InputTxtView = (TextView) v.findViewById(R.id.txtViewSpiritsInput1);
        spirits2InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput2);
        spirits3InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput3);
        spirits4InputTxtView = (TextView)v.findViewById(R.id.txtViewSpiritsInput4);

        // set click listener to text view so number pad is shown when user clicks
        // on the text input
        beer1InputTxtView.setOnClickListener(this);
        beer2InputTxtView.setOnClickListener(this);
        beer3InputTxtView.setOnClickListener(this);
        beer4InputTxtView.setOnClickListener(this);
        wine1InputTxtView.setOnClickListener(this);
        wine2InputTxtView.setOnClickListener(this);
        wine3InputTxtView.setOnClickListener(this);
        wine4InputTxtView.setOnClickListener(this);
        spirits1InputTxtView.setOnClickListener(this);
        spirits2InputTxtView.setOnClickListener(this);
        spirits3InputTxtView.setOnClickListener(this);
        spirits4InputTxtView.setOnClickListener(this);

        // assign view to text view
        beer1ATxtView = (TextView)v.findViewById(R.id.txtViewBeer1A);
        beer1BTxtView = (TextView)v.findViewById(R.id.txtViewBeer1B);
        beer2ATxtView = (TextView)v.findViewById(R.id.txtViewBeer2A);
        beer2BTxtView = (TextView)v.findViewById(R.id.txtViewBeer2B);
        beer3ATxtView = (TextView)v.findViewById(R.id.txtViewBeer3A);
        beer3BTxtView = (TextView)v.findViewById(R.id.txtViewBeer3B);
        beer4ATxtView = (TextView)v.findViewById(R.id.txtViewBeer4A);
        beer4BTxtView = (TextView)v.findViewById(R.id.txtViewBeer4B);
        wine1ATxtView = (TextView)v.findViewById(R.id.txtViewWine1A);
        wine1BTxtView = (TextView)v.findViewById(R.id.txtViewWine1B);
        wine2ATxtView = (TextView)v.findViewById(R.id.txtViewWine2A);
        wine2BTxtView = (TextView)v.findViewById(R.id.txtViewWine2B);
        wine3ATxtView = (TextView)v.findViewById(R.id.txtViewWine3A);
        wine3BTxtView = (TextView)v.findViewById(R.id.txtViewWine3B);
        wine4ATxtView = (TextView)v.findViewById(R.id.txtViewWine4A);
        wine4BTxtView = (TextView)v.findViewById(R.id.txtViewWine4B);
        spirits1ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits1A);
        spirits1BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits1B);
        spirits2ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits2A);
        spirits2BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits2B);
        spirits3ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits3A);
        spirits3BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits3B);
        spirits4ATxtView = (TextView)v.findViewById(R.id.txtViewSpirits4A);
        spirits4BTxtView = (TextView)v.findViewById(R.id.txtViewSpirits4B);

        // set click listener
        beer1ATxtView.setOnClickListener(this);
        beer1BTxtView.setOnClickListener(this);
        beer2ATxtView.setOnClickListener(this);
        beer2BTxtView.setOnClickListener(this);
        beer3ATxtView.setOnClickListener(this);
        beer3BTxtView.setOnClickListener(this);
        beer4ATxtView.setOnClickListener(this);
        beer4BTxtView.setOnClickListener(this);
        wine1ATxtView.setOnClickListener(this);
        wine1BTxtView.setOnClickListener(this);
        wine2ATxtView.setOnClickListener(this);
        wine2BTxtView.setOnClickListener(this);
        wine3ATxtView.setOnClickListener(this);
        wine3BTxtView.setOnClickListener(this);
        wine4ATxtView.setOnClickListener(this);
        wine4BTxtView.setOnClickListener(this);
        spirits1ATxtView.setOnClickListener(this);
        spirits1BTxtView.setOnClickListener(this);
        spirits2ATxtView.setOnClickListener(this);
        spirits2BTxtView.setOnClickListener(this);
        spirits3ATxtView.setOnClickListener(this);
        spirits3BTxtView.setOnClickListener(this);
        spirits4ATxtView.setOnClickListener(this);
        spirits4BTxtView.setOnClickListener(this);

        // initialise shared preference object
        sharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);


        // this will only call method when number pad has been displayed
        if(DRINKS_ENTERED_ALREADY)
        {
            // load number of drinks consumed into text view
            getDrinksConsumed();
        }

        return v;
        */
    //}


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        /*
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        _tabHost.setOnTabChangedListener(this);

        if (tabDefinitions.length > 0) {
            onTabChanged(tabDefinitions[0].getId());
        }
        */
    }

    //
    // Internal Members
    //
    /**
     * Creates a {@link TabSpec} based on the specified parameters.
     * @param inflater The {@link LayoutInflater} responsible for creating {@link View}s.
     * @param tabHost The {@link TabHost} used to create new {@link TabSpec}s.
     * @param root The root {@link View} for the {@link Fragment}.
     * @param tabDefinition The {@link TabDefinition} that defines what the tab will look and act like.
     * @return A new {@link TabSpec} instance.
     */
    private TabHost.TabSpec createTab(LayoutInflater inflater,
                                      TabHost tabHost, View root, TabDefinition tabDefinition)
    {
        ViewGroup tabsView = (ViewGroup)root.findViewById(android.R.id.tabs);
        View tabView = tabDefinition.createTabView(inflater, tabsView);

        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabDefinition.getId());
        tabSpec.setIndicator(tabView);
        tabSpec.setContent(tabDefinition.getTabContentViewId());
        return tabSpec;
    }

    /**
     * Called when switching between tabs.
     * @param tabId The unique identifier for the tab.
     * @param fragment The {@link Fragment} to swap in for the tab.
     * @param containerId The layout ID for the {@link View} that houses the tab's content.
     */
    private void updateTab(String tabId, Fragment fragment, int containerId)
    {
        final FragmentManager manager = getFragmentManager();

        if (manager.findFragmentByTag(tabId) == null)
        {
            manager.beginTransaction()
                    .replace(containerId, fragment, tabId)
                    .commit();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        // if user enters drink for the first time the DRINKS_ENTERED_ALREADY will be false
        // we change the value after the number pad has been shown.
        //if(!DRINKS_ENTERED_ALREADY) { DRINKS_ENTERED_ALREADY = true; }

        switch(view.getId())
        {
            case R.id.lLayoutBeerRow1:
                showNumberPad(BEER_ROW_1_CLICKED);
                break;

            case R.id.lLayoutBeerRow2:
                showNumberPad(BEER_ROW_2_CLICKED);
                break;

            case R.id.lLayoutBeerRow3:
                showNumberPad(BEER_ROW_3_CLICKED);
                break;

            case R.id.lLayoutBeerRow4:
                showNumberPad(BEER_ROW_4_CLICKED);
                break;

            case R.id.lLayoutWineRow1:
                showNumberPad(WINE_ROW_1_CLICKED);
                break;

            case R.id.lLayoutWineRow2:
                showNumberPad(WINE_ROW_2_CLICKED);
                break;

            case R.id.lLayoutWineRow3:
                showNumberPad(WINE_ROW_3_CLICKED);
                break;

            case R.id.lLayoutWineRow4:
                showNumberPad(WINE_ROW_4_CLICKED);
                break;

            case R.id.lLayoutSpiritsRow1:
                showNumberPad(SPIRIT_ONE_CLICKED);
                break;

            case R.id.lLayoutSpiritsRow2:
                showNumberPad(SPIRIT_TWO_CLICKED);
                break;

            case R.id.lLayoutSpiritsRow3:
                showNumberPad(SPIRIT_THREE_CLICKED);
                break;

            case R.id.lLayoutSpiritsRow4:
                showNumberPad(SPIRIT_FOUR_CLICKED);
                break;
        }

        return false;
    }

    public void onClick(View v)
    {
        // for debugging
        Log.d(getClass().getCanonicalName(), "TextView was clicked!");
        // display toast to check if click event works
        // must call getActivity() when calling Toast from fragment
        //Toast.makeText(getActivity(), "Beer Input TextView click event works", Toast.LENGTH_LONG).show();

        // if user enters drink for the first time the DRINKS_ENTERED_ALREADY will be false
        // we change the value after the number pad has been shown.
        //if(!DRINKS_ENTERED_ALREADY) { DRINKS_ENTERED_ALREADY = true; }

        switch(v.getId())
        {
            // handle click event for beer row 1
            // which includes image view, 3 text views
            case R.id.imgViewBeer1:
            case R.id.txtViewBeer1A:
            case R.id.txtViewBeer1B:
            case R.id.txtViewBeerInput1:
                showNumberPad(BEER_ROW_1_CLICKED);
                break;

            case R.id.imgViewBeer2:
            case R.id.txtViewBeer2A:
            case R.id.txtViewBeer2B:
            case R.id.txtViewBeerInput2:
                showNumberPad(BEER_ROW_2_CLICKED);
                break;

            case R.id.imgViewBeer3:
            case R.id.txtViewBeer3A:
            case R.id.txtViewBeer3B:
            case R.id.txtViewBeerInput3:
                showNumberPad(BEER_ROW_3_CLICKED);
                break;

            case R.id.imgViewBeer4:
            case R.id.txtViewBeer4A:
            case R.id.txtViewBeer4B:
            case R.id.txtViewBeerInput4:
                showNumberPad(BEER_ROW_4_CLICKED);
                break;

            // handle click event for wine input
            case R.id.imgViewWine1:
            case R.id.txtViewWine1A:
            case R.id.txtViewWine1B:
            case R.id.txtViewWineInput1:
                showNumberPad(WINE_ROW_1_CLICKED);
                break;

            case R.id.imgViewWine2:
            case R.id.txtViewWine2A:
            case R.id.txtViewWine2B:
            case R.id.txtViewWineInput2:
                showNumberPad(WINE_ROW_2_CLICKED);
                break;

            case R.id.imgViewWine3:
            case R.id.txtViewWine3A:
            case R.id.txtViewWine3B:
            case R.id.txtViewWineInput3:
                showNumberPad(WINE_ROW_3_CLICKED);
                break;

            case R.id.imgViewWine4:
            case R.id.txtViewWine4A:
            case R.id.txtViewWine4B:
            case R.id.txtViewWineInput4:
                showNumberPad(WINE_ROW_4_CLICKED);
                break;

            // handle click event for spirits input
            case R.id.imgViewSpirits1:
            case R.id.txtViewSpirits1A:
            case R.id.txtViewSpirits1B:
            case R.id.txtViewSpiritsInput1:
                showNumberPad(SPIRIT_ONE_CLICKED);
                break;

            case R.id.imgViewSpirits2:
            case R.id.txtViewSpirits2A:
            case R.id.txtViewSpirits2B:
            case R.id.txtViewSpiritsInput2:
                showNumberPad(SPIRIT_TWO_CLICKED);
                break;

            case R.id.imgViewSpirits3:
            case R.id.txtViewSpirits3A:
            case R.id.txtViewSpirits3B:
            case R.id.txtViewSpiritsInput3:
                showNumberPad(SPIRIT_THREE_CLICKED);
                break;

            case R.id.imgViewSpirits4:
            case R.id.txtViewSpirits4A:
            case R.id.txtViewSpirits4B:
            case R.id.txtViewSpiritsInput4:
                showNumberPad(SPIRIT_FOUR_CLICKED);
                break;


        }

    }


    void showNumberPad(int txtViewClicked)
    {
        FragmentManager fm = getFragmentManager();
        NumberPadFragment numberPadFragment = new NumberPadFragment();
        numberPadFragment.setTargetFragment(this, txtViewClicked);
        numberPadFragment.show(fm, "Alcohol Consumption");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bundle bundle = data.getExtras();
        int standardDrinks = bundle.getInt("NumOfDrinks");

        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case BEER_ROW_1_CLICKED:
                    // assign value from number pad to text view
                    beer1InputTxtView.setText(Integer.toString(standardDrinks));
                    // for testing: check if value is passed back to fragment
                    //Toast.makeText(getActivity(),  "Standard Drinks = " + standardDrinks, Toast.LENGTH_LONG).show();
                    break;

                case BEER_ROW_2_CLICKED:
                    beer2InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case BEER_ROW_3_CLICKED:
                    beer3InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case BEER_ROW_4_CLICKED:
                    beer4InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case WINE_ROW_1_CLICKED:
                    wine1InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                // TODO: add rest of code to handle number of drinks selected
            }
        }


        // get values from text view
        int numOfSmBeers = Integer.parseInt(beer1InputTxtView.getText().toString());
        int numOfLgBeers = Integer.parseInt(beer2InputTxtView.getText().toString());
        int numOfBeerBottles = Integer.parseInt(beer3InputTxtView.getText().toString());
        int numOfBeerCans = Integer.parseInt(beer4InputTxtView.getText().toString());
        int numOfSparkWine = Integer.parseInt(wine1InputTxtView.getText().toString());


        // save values to shared preferences, values are saved as (Key, Value) pairs
        // key value is stored as string in strings.xml to allow other fragments easy access
        // to them
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(getString(R.string.sm_beer_key), numOfSmBeers);
        editor.putInt(getString(R.string.lg_beer_key), numOfLgBeers);
        editor.putInt(getString(R.string.beer_bottle_key), numOfBeerBottles);
        editor.putInt(getString(R.string.beer_can_key), numOfBeerCans);
        editor.putInt(getString(R.string.wine_sparkling_key), numOfSparkWine);
        // TODO: add code to finish saving to shared preferences
        editor.commit();


    }

    private void getDrinksConsumed()
    {
        /*
         * get values from shared preferences and display in text view
         */
        if(sharedPrefs.contains(getActivity().getString(R.string.sm_beer_key)))
        {
            beer1InputTxtView.setText(Integer.toString(sharedPrefs.getInt(getActivity().getString(R.string.sm_beer_key), 0)));
        }

        if(sharedPrefs.contains(getActivity().getString(R.string.lg_beer_key)))
        {
            beer2InputTxtView.setText(Integer.toString(sharedPrefs.getInt(getActivity().getString(R.string.lg_beer_key), 0)));
        }

        if(sharedPrefs.contains(getActivity().getString(R.string.beer_bottle_key)))
        {
            beer3InputTxtView.setText(Integer.toString(sharedPrefs.getInt(getActivity().getString(R.string.beer_bottle_key), 0)));
        }

        if(sharedPrefs.contains(getActivity().getString(R.string.beer_can_key)))
        {
            beer4InputTxtView.setText(Integer.toString(sharedPrefs.getInt(getActivity().getString(R.string.beer_can_key), 0)));
        }

        // TODO: add code to load drinks consumed if user returns to BAC screen
    }

    public int testNumberOfDrinks()
    {
        /*
         * example test data
         */
        int numOfDrinksSuccess = 10;        // use this value to return a success test result
        int numOfDrinksFail = 5;            // this value for a fail test result

        return numOfDrinksFail;
    }

}
