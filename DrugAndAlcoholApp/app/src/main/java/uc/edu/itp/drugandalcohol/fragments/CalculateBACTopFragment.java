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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;


import org.w3c.dom.Text;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.SimpleTabDefinition;
import uc.edu.itp.drugandalcohol.controller.TabDefinition;
import uc.edu.itp.drugandalcohol.model.AlcoholType;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalculateBACTopFragment extends Fragment
        implements TabHost.OnTabChangeListener, View.OnClickListener
{

    // set text for tab heading
    private final TabDefinition[] tabDefinitions = new TabDefinition[] {
            new SimpleTabDefinition(R.id.tab1, R.layout.tab_heading,
                    R.string.beer_tab, R.id.tabTitle, new Fragment()),
            new SimpleTabDefinition(R.id.tab2, R.layout.tab_heading,
                    R.string.wine_tab, R.id.tabTitle, new Fragment()),
            new SimpleTabDefinition(R.id.tab3, R.layout.tab_heading,
                    R.string.spirits_tab, R.id.tabTitle, new Fragment()),

    };


    // fields
    private final int NUMBER_PAD_REQUEST_CODE = 1;
    private static final String BeerSmall = "beerSmallKey";
    private static final String BeerLarge = "beerLargeKey";
    private static final String BeerBottle = "beerBottleKey";
    private static final String BeerCan = "beerCanKey";

    private static final int SM_BEER_CLICKED = 0;
    private static final int LG_BEER_CLICKED = 1;
    private static final int BEER_BOTTLE_CLICKED = 2;
    private static final int BEER_CAN_CLICKED = 3;


    RelativeLayout relativeLayoutBeer;
    View _viewRoot;
    TabHost _tabHost;
    TextView beer1InputTxtView, beer2InputTxtView, beer3InputTxtView, beer4InputTxtView;
    SharedPreferences sharedPrefs;

    public CalculateBACTopFragment() {
        // Required empty public constructor
    }


    //
    // Public Members
    //
    @Override
    public void onTabChanged(String tabId)
    {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // inflate fragment_calculate_bac_top as the fragment view
        _viewRoot = inflater.inflate(R.layout.fragment_calculate_bac_top, null);

        // initialise the tabhost
        _tabHost = (TabHost)_viewRoot.findViewById(android.R.id.tabhost);
        _tabHost.setup();

        for (TabDefinition tab : tabDefinitions) {
            _tabHost.addTab(createTab(inflater, _tabHost, _viewRoot, tab));
        }

        beer1InputTxtView = (TextView) _viewRoot.findViewById(R.id.txtViewBeerInput1);
        beer2InputTxtView = (TextView) _viewRoot.findViewById(R.id.txtViewBeerInput2);
        beer3InputTxtView = (TextView) _viewRoot.findViewById(R.id.txtViewBeerInput3);
        beer4InputTxtView = (TextView) _viewRoot.findViewById(R.id.txtViewBeerInput4);

        beer1InputTxtView.setOnClickListener(this);
        beer2InputTxtView.setOnClickListener(this);
        beer3InputTxtView.setOnClickListener(this);
        beer4InputTxtView.setOnClickListener(this);

        // initialise shared preference object
        sharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);

        return _viewRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        _tabHost.setOnTabChangedListener(this);

        if (tabDefinitions.length > 0) {
            onTabChanged(tabDefinitions[0].getId());
        }
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

    public void onClick(View v)
    {
        // for debugging
        Log.d(getClass().getCanonicalName(), "TextView was clicked!");
        // display toast to check if click event works
        // must call getActivity() when calling Toast from fragment
        //Toast.makeText(getActivity(), "Beer Input TextView click event works", Toast.LENGTH_LONG).show();

        switch(v.getId())
        {
            // handle click event for beer input
            case R.id.txtViewBeerInput1:
                showNumberPad(SM_BEER_CLICKED);
                break;
            case R.id.txtViewBeerInput2:
                showNumberPad(LG_BEER_CLICKED);
                break;

            case R.id.txtViewBeerInput3:
                showNumberPad(BEER_BOTTLE_CLICKED);
                break;
            case R.id.txtViewBeerInput4:
                showNumberPad(BEER_CAN_CLICKED);
                break;

             /*
            // handle click event for wine input
            case R.id.txtViewWineInput1:
                showNumberPad();
                break;
            case R.id.txtViewWineInput2:
                showNumberPad();
                break;
            case R.id.txtViewWineInput3:

                showNumberPad();
                break;
            case R.id.txtViewWineInput4:

                showNumberPad();
                break;

            // handle click event for spirits input
            case R.id.txtViewSpiritsInput1:

                showNumberPad();
                break;
            case R.id.txtViewSpiritsInput2:

                showNumberPad();
                break;
            case R.id.txtViewSpiritsInput3:

                showNumberPad();
                break;
            case R.id.txtViewSpiritsInput4:

                showNumberPad();
                break;
            */

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
                case SM_BEER_CLICKED:
                    //standardDrinks = bundle.getInt("NumOfDrinks");
                    // assign value from number pad to text view
                    beer1InputTxtView.setText(Integer.toString(standardDrinks));
                    // for testing: check if value is passed back to fragment
                    //Toast.makeText(getActivity(),  "Standard Drinks = " + standardDrinks, Toast.LENGTH_LONG).show();
                    break;

                case LG_BEER_CLICKED:
                    //standardDrinks = bundle.getInt("NumOfDrinks");
                    beer2InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case BEER_BOTTLE_CLICKED:
                    beer3InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

                case BEER_CAN_CLICKED:
                    beer4InputTxtView.setText(Integer.toString(standardDrinks));
                    break;

            }
        }


        // get values from text view

        int numOfSmBeers = Integer.parseInt(beer1InputTxtView.getText().toString());
        int numOfLgBeers = Integer.parseInt(beer2InputTxtView.getText().toString());
        int numOfBeerBottles = Integer.parseInt(beer3InputTxtView.getText().toString());
        int numOfBeerCans = Integer.parseInt(beer4InputTxtView.getText().toString());

        // save values to shared preferences
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(BeerSmall, numOfSmBeers);
        editor.putInt(BeerLarge, numOfLgBeers);
        editor.putInt(BeerBottle, numOfBeerBottles);
        editor.putInt(BeerCan, numOfBeerCans);
        editor.commit();


    }

}
