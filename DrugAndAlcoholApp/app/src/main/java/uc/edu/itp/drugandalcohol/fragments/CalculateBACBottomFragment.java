package uc.edu.itp.drugandalcohol.fragments;



import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.UserDetails;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalculateBACBottomFragment extends Fragment
    implements View.OnClickListener
{
    private static final String TAG = "CalculateBACBottomFragment";

    EditText userWeightEditTxt;
    //Button calculateBACBtn;
    Button calculateBAC2Btn;

    SharedPreferences drinkSharedPrefs, userSharedPrefs;
    SharedPreferences.Editor editor;



    //private static final String USER_WEIGHT = "userWeightKey";

    public CalculateBACBottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_calculate_bac_bottom2, null);

        //displayBACtxtView = (TextView)v.findViewById(R.id.txtViewDisplayBAC);
        //calculateBACBtn = (Button)v.findViewById(R.id.btnCalculateBAC);
        //calculateBACBtn.setOnClickListener(this);



        userWeightEditTxt = (EditText)v.findViewById(R.id.editTxtUserWeight);

        calculateBAC2Btn = (Button)v.findViewById(R.id.btnCalculateBAC2);
        calculateBAC2Btn.setOnClickListener(this);

        drinkSharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);
        userSharedPrefs = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);



        // dsiplay user weight, value was saved to shared preferences if they entered their
        // details in user details screen, if no value was entered a default value of 0.00 is
        // displayed
        //int noWeightEntered = 0;
        //int userWeight = userSharedPrefs.getInt(getString(R.string.user_weight_key), noWeightEntered);
        //userWeightEditTxt.setText(Integer.toString(userWeight));

        // display user weight user UserDetails class
        userWeightEditTxt.setText(Integer.toString(UserDetails.getInstance().getWeight()));

        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            //case R.id.btnCalculateBAC:
                //calculateBAC();
                //break;

            case R.id.btnCalculateBAC2:
                calculateBAC();
                showBACDialog();
                break;

        }
    }



    void showBACDialog()
    {
        FragmentManager fm = getFragmentManager();
        DisplayBACDialogFragment bacDialogFragment = new DisplayBACDialogFragment();
        bacDialogFragment.setTargetFragment(this, 0);
        bacDialogFragment.show(fm, "Calculate BAC Bottom");

    }


    private void calculateBAC()
    {
        float totalStandardDrinks;
        float bacValue;

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
        float smBeerStandardDrinks = 1.1f * numOfSmBeers;
        float lgBeerStandardDrinks = 1.6f * numOfLgBeers;
        float bottleBeerStandardDrinks = 1.4f * numOfBeerBottles;
        float canBeerStandDrinks = 1.4f * numOfBeerCans;

        float sparklingWineStandardDrinks = 1.4f * numOfSparklingWine;
        float redWineStandardDrinks = 1.6f * numOfRedWine;
        float whiteWineStandardDrinks = 1.4f * numOfWhiteWine;
        float bottleWineStandardDrinks = 8.0f * numOfBottleWine;

        // calculate total standard drinks
        totalStandardDrinks = smBeerStandardDrinks + lgBeerStandardDrinks + bottleBeerStandardDrinks + canBeerStandDrinks
                + sparklingWineStandardDrinks + redWineStandardDrinks + whiteWineStandardDrinks + bottleWineStandardDrinks;

        // call method to calculate bac using formula
        bacValue = bacFormula(totalStandardDrinks);

        // save BAC value to shared preferences
        editor = userSharedPrefs.edit();
        editor.putFloat(getString(R.string.user_current_bac_key), bacValue);
        editor.commit();

        //DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //displayBACtxtView.setText(decimalFormat.format(bacValue));

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
    private float bacFormula(float numOfDrinks)
    {
        float N, H, M;
        float bac;
        boolean gender;

        N = numOfDrinks;
        H = 1;          // for testing hours since drinking is set to 1hr
        // TODO: add code to get hours since drinking from spinner

        //M = userSharedPrefs.getInt(getString(R.string.user_weight_key), 0);
        M = UserDetails.getInstance().getWeight();

        // get gender value from shared prefs, if no value is stored we return a default
        // value of true to represent a male.
        //gender = userSharedPrefs.getBoolean(getString(R.string.user_gender_key), true);
        gender = UserDetails.getInstance().getGender();

        // true = male
        // false = female
        if(gender)
        {
            // calculate BAC for a male
            bac = ((10 * N) - (7.5f * H)) / (6.8f * M);
        }
        else
        {
            // calculate BAC for female
            bac = ((10 * N) - (7.5f * H)) / (5.5f * M);
        }

        Log.d(TAG, Float.toString(bac));

        return bac;

    }


}
