package uc.edu.itp.drugandalcohol.fragments;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalculateBACBottomFragment extends Fragment
    implements View.OnClickListener
{
    TextView displayBACtxtView;
    Button calculateBACBtn;

    SharedPreferences sharedPrefs;

    private static final String BeerSmall = "beerSmallKey";
    private static final String BeerLarge = "beerLargeKey";
    private static final String BeerBottle = "beerBottleKey";
    private static final String BeerCan = "beerCanKey";

    public CalculateBACBottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_calculate_bac_bottom, null);

        displayBACtxtView = (TextView)v.findViewById(R.id.txtViewDisplayBAC);
        calculateBACBtn = (Button)v.findViewById(R.id.btnCalculateBAC);

        calculateBACBtn.setOnClickListener(this);

        sharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);

        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCalculateBAC:
                calculateBAC();
                break;

        }
    }


    private void calculateBAC()
    {
        double totalStandardDrinks, bacValue;
        int numOfSmBeers = sharedPrefs.getInt(BeerSmall, 0);
        int numOfLgBeers = sharedPrefs.getInt(BeerLarge, 0);
        //int numOfBeerBottles = sharedPrefs.getInt(BeerBottle, 0);
        //int numOfBeerCans = sharedPrefs.getInt(BeerCan, 0);

        // apply standard drinks value to number of drinks
        // e.g 1 small beer = 1.1 standard drinks
        //       large beer = 1.6 standard drinks
        double smBeerStandardDrinks = 1.1 * numOfSmBeers;
        double lgBeerStandardDrinks = 1.6 * numOfLgBeers;

        // use test values for debugging
        // BAC should display 14 (5 + 2 + 3 + 4)
        totalStandardDrinks = smBeerStandardDrinks + lgBeerStandardDrinks;
        bacValue = bacFormula(totalStandardDrinks);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        displayBACtxtView.setText(decimalFormat.format(bacValue));

        /* can use this code to load value of number of drinks into text view
        // get values from shared preferences
        if(sharedPrefs.contains(BeerSmall))
        {
            // return the value of BeerSmall in shared preferences
            // if no value return 0.
            numOfSmBeers = sharedPrefs.getInt(BeerSmall, 0);
        }

        if(sharedPrefs.contains(BeerLarge))
        {
            numOfLgBeers = sharedPrefs.getInt(BeerLarge, 0);
        }

        if(sharedPrefs.contains(BeerBottle))
        {
            numOfBeerBottles = sharedPrefs.getInt(BeerBottle, 0);
        }

        if(sharedPrefs.contains(BeerCan))
        {
            numOfBeerCans = sharedPrefs.getInt(BeerCan, 0);
        }
        */

        //totalStandardDrinks = numOfSmBeers + numOfLgBeers + numOfBeerBottles + numOfBeerCans;


    }

    /*
     * The formula to calculate alcohol blood content is
     * based on Widmark's formula:
     *
     * BAC = (10N - 7.5H) / 6.8M        *for males
     * BAC = (10N - 7.5H) / 5.5M        *for females
     *
     * where N = number of standard drinks consumed
     *       H = hours since started drinking
     *       M = weight in kg
     */
    private double bacFormula(double numOfDrinks)
    {
        // TODO: add code to check if user is male or female then apply appropriate formula

        double N, H, M, bac = 0;

        N = numOfDrinks;
        H = 1;
        M = 70;

        bac = ((10 * N) - (7.5 * H)) / (6.8 * M);

        return bac;

    }


}
