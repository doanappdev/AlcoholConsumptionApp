package uc.edu.itp.drugandalcohol.fragments;



import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.UserDetails;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CalculateBACBottomFragment extends Fragment
    implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
    private static final String TAG = "CalculateBACBottomFragment";

    // array of hour values stored as floats which correspond to
    // the string array which is used in the spinner for hours drinking
    private static final float[] HOURS = {0.5f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f};

    EditText userWeightEditTxt;
    //Button calculateBACBtn;
    Button calculateBAC2Btn;

    SharedPreferences drinkSharedPrefs, userSharedPrefs;
    SharedPreferences.Editor editor;

    Spinner spinnerHrsDrinking;

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

        spinnerHrsDrinking = (Spinner)v.findViewById(R.id.spinnerHrsDrinking);
        spinnerHrsDrinking.setOnItemSelectedListener(this);

        userWeightEditTxt = (EditText)v.findViewById(R.id.editTxtUserWeight);

        calculateBAC2Btn = (Button)v.findViewById(R.id.btnCalculateBAC2);
        calculateBAC2Btn.setOnClickListener(this);

        drinkSharedPrefs = getActivity().getSharedPreferences("DrinkPrefs", Context.MODE_PRIVATE);
        userSharedPrefs = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // dsiplay user weight, value was saved to shared preferences if they entered their
        // details in user details screen, if no value was entered a default value of 0.00 is
        // displayed
        //int noWeightEntered = 0;
        //int userWeight = drinkingSharedPrefs.getInt(getString(R.string.user_weight_key), noWeightEntered);
        //userWeightEditTxt.setText(Integer.toString(userWeight));

        // display user weight from UserDetails class
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

    /*
    * spinner click listener
    * assign the selected item on spinner to variable
    */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        // don't really need a switch here, but leave in case next group
        // decides to another spinner in fragment
        switch(parent.getId())
        {
            case R.id.spinnerHrsDrinking:

                //UserDetails.getInstance().setHrsSinceDrinking(Float.parseFloat(parent.getSelectedItem().toString()));
                UserDetails.getInstance().setHrsSinceDrinking(HOURS[pos]);

                Toast.makeText(getActivity(),
                        "Hours: " + UserDetails.getInstance().getHrsSinceDrinking(),
                        Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /*
     * this method is required when using onItemSelectedListener
     */
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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
        //bacValue = bacFormula(totalStandardDrinks);
        bacFormula(totalStandardDrinks);

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
    private void bacFormula(float numOfDrinks)
    {
        float N, H, M;
        float bac;
        boolean gender;

        N = numOfDrinks;
        //H = 1;          // for testing hours since drinking is set to 1hr
        H = UserDetails.getInstance().getHrsSinceDrinking();

        //M = drinkingSharedPrefs.getInt(getString(R.string.user_weight_key), 0);
        M = (float)UserDetails.getInstance().getWeight();

        // test if weight is equal to 0
        if(M == 0f) { userWeightEditTxt.setError("Enter your weight"); }

        // get gender value from shared prefs, if no value is stored we return a default
        // value of true to represent a male.
        //gender = drinkingSharedPrefs.getBoolean(getString(R.string.user_gender_key), true);
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

        saveDrinkingValues(numOfDrinks, H, bac);

        // for testing to check values
        Log.d(TAG, Float.toString(bac));

        //return bac;

    }

    // save BAC to shared preferences, this will save the value
    // even when the user exits app.
    public void saveDrinkingValues(float totalDrinks, float hrsDrinking, float bac)
    {
        editor = drinkSharedPrefs.edit();
        editor.putFloat(getString(R.string.drinking_number_of_drinks_key), totalDrinks);
        editor.putFloat(getString(R.string.drinking_hrs_drinking_key), hrsDrinking);
        editor.putFloat(getString(R.string.drinking_current_bac_key), bac);
        editor.commit();
    }

}
