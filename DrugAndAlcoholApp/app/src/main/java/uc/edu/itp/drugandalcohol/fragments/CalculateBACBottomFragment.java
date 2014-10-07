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

    SharedPreferences drinkSharedPrefs, userSharedPrefs;

    private static final String USER_WEIGHT = "userWeightKey";

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

        drinkSharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);
        userSharedPrefs = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);


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
        int numOfSmBeers = drinkSharedPrefs.getInt(getString(R.string.sm_beer_key), 0);
        int numOfLgBeers = drinkSharedPrefs.getInt(getString(R.string.lg_beer_key), 0);
        int numOfBeerBottles = drinkSharedPrefs.getInt(getString(R.string.beer_bottle_key), 0);
        int numOfBeerCans = drinkSharedPrefs.getInt(getString(R.string.beer_can_key), 0);
        int numOfSparklingWine = drinkSharedPrefs.getInt(getString(R.string.wine_sparkling_key), 0);
        int numOfRedWine = drinkSharedPrefs.getInt(getString(R.string.wine_red_key), 0);
        int numOfWhiteWine = drinkSharedPrefs.getInt(getString(R.string.wine_white_key), 0);
        int numOfBottleWine = drinkSharedPrefs.getInt(getString(R.string.wine_bottle_key), 0);

        // apply standard drinks value to number of drinks
        // e.g 1 small beer = 1.1 standard drinks
        //       large beer = 1.6 standard drinks
        double smBeerStandardDrinks = 1.1 * numOfSmBeers;
        double lgBeerStandardDrinks = 1.6 * numOfLgBeers;
        double bottleBeerStandardDrinks = 1.4 * numOfBeerBottles;
        double canBeerStandDrinks = 1.4 * numOfBeerCans;

        double sparklingWineStandardDrinks = 1.4 * numOfSparklingWine;
        double redWineStandardDrinks = 1.6 * numOfRedWine;
        double whiteWineStandardDrinks = 1.4 * numOfWhiteWine;
        double bottleWineStandardDrinks = 8.0 * numOfBottleWine;

        totalStandardDrinks = smBeerStandardDrinks + lgBeerStandardDrinks + bottleBeerStandardDrinks + canBeerStandDrinks
                + sparklingWineStandardDrinks + redWineStandardDrinks + whiteWineStandardDrinks + bottleWineStandardDrinks;

        // call method to calculate bac using formula
        bacValue = bacFormula(totalStandardDrinks);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        displayBACtxtView.setText(decimalFormat.format(bacValue));


    }

    /*
     * formula to calculate blood alcohol content is
     * based on Widmark's formula:
     *
     * BAC = (10N - 7.5H) / 6.8M        -for males
     * BAC = (10N - 7.5H) / 5.5M        -for females
     *
     * where N = number of standard drinks consumed
     *       H = hours since started drinking
     *       M = weight in kg
     */
    private double bacFormula(double numOfDrinks)
    {
        // TODO: add code to check if user is male or female then apply appropriate formula

        double N, H, M, bac;

        N = numOfDrinks;
        H = 1;
        M = userSharedPrefs.getInt(USER_WEIGHT, 0);

        bac = ((10 * N) - (7.5 * H)) / (6.8 * M);

        return bac;

    }


}
