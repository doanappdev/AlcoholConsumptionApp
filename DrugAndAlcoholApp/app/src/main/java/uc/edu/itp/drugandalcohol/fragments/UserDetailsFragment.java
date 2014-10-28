package uc.edu.itp.drugandalcohol.fragments;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.model.BuddyContact;
import uc.edu.itp.drugandalcohol.model.UserDetails;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class UserDetailsFragment extends Fragment
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    LinearLayout pregnantLayout;
    SharedPreferences userSharedPrefs;

    Button acceptBtn, cancelBtn, editBtn;

    EditText userAgeEditTxt, userWeightEditTxt, buddyNameEditTxt, buddyNumberEditTxt;
    Switch genderSwitch, pregnantSwitch;

    private boolean isMALE = true;
    private boolean isPREGNANT = false;

    BuddyContact buddyContact;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_user_details, container, false);

        buddyContact = new BuddyContact();

        userSharedPrefs = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        userAgeEditTxt = (EditText)v.findViewById(R.id.editTxtAge);
        userWeightEditTxt = (EditText)v.findViewById(R.id.editTxtWeight);
        buddyNameEditTxt = (EditText)v.findViewById(R.id.editTxtBuddyName);
        buddyNumberEditTxt = (EditText)v.findViewById(R.id.editTxtBuddyNumber);

        acceptBtn = (Button)v.findViewById(R.id.btnAccept);
        cancelBtn = (Button)v.findViewById(R.id.btnCancel);

        genderSwitch = (Switch)v.findViewById(R.id.switchGender);

        acceptBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        genderSwitch.setOnCheckedChangeListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAccept:
                saveUserPrefs();
                break;

            case R.id.btnCancel:
                onStop();
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        pregnantLayout = (LinearLayout)getActivity().findViewById(R.id.lLayoutPregnant);

        switch(buttonView.getId())
        {
            // handle click event for gender switch
            case R.id.switchGender:

                if(genderSwitch.isChecked())
                {
                    isMALE = true;      //  user is male
                    // hide pregnant switch
                    pregnantLayout.setVisibility(LinearLayout.GONE);

                }
                else
                {
                    isMALE = false;     //  user is female
                    // display pregnant switch
                    pregnantLayout.setVisibility(LinearLayout.VISIBLE);
                }

                break;

            // handle click event for pregnant switch
            case R.id.switchPregnant:

                if(pregnantSwitch.isChecked())
                {
                    isPREGNANT = true;      //  user is male
                }
                else
                {
                    isPREGNANT = false;     //  user is female
                }

                break;

        }
    }


    /*
     * Save details entered by user
     */
    private void saveUserPrefs()
    {
        // get values from UI
        //int buddyNumInt = checkInputNumber();
        //int userAge = checkInputAge();
        //int userWeight = checkInputWeight();
        //String buddyName = checkInputName();

        // no need to instantiate UserDetails object because it
        // is a singleton object
        UserDetails.getInstance().setAge(checkInputAge());
        UserDetails.getInstance().setWeight(checkInputWeight());
        UserDetails.getInstance().setGender(isMALE);
        UserDetails.getInstance().setPregnant(isPREGNANT);
        UserDetails.getInstance().setStandardDrinks(0);

        buddyContact.setName(checkInputName());
        buddyContact.setNumber(checkInputNumber());

        /*
        // save values to user shared preferences
        SharedPreferences.Editor editor = drinkingSharedPrefs.edit();
        editor.putInt(getString(R.string.user_age_key), userAge);
        editor.putInt(getString(R.string.user_weight_key), userWeight);
        editor.putInt(getString(R.string.buddy_number_key), buddyNumInt);
        editor.putString(getString(R.string.buddy_name_key), buddyName);
        editor.putBoolean(getString(R.string.user_gender_key), isMALE);
        editor.commit();
        */

        // display toast to confirm details saved (for testing)
        Toast.makeText(getActivity(),  "User Details Entered\nUser Age: " + UserDetails.getInstance().getAge() +
                                       "\nUser Weight: " + UserDetails.getInstance().getWeight() +
                                       "\nMale: " + isMALE +
                                       "\nPregnant: " + isPREGNANT +
                                       "\nBuddy Name: " + buddyContact.getName() +
                                       "\nBuddy Number: " + buddyContact.getNumber(),
                                        Toast.LENGTH_LONG).show();


    }

    /*
     * check the value for age entered by the user
     */
    public int checkInputAge()
    {
        int userAge = 0;
        // get value entered from edit text box
        String strUserAge = userAgeEditTxt.getText().toString();

        if(strUserAge.isEmpty())
        {
            // no value entered
            userAgeEditTxt.setError("Please enter your age");
        }
        else
        {
            // age entered, convert to integer
            userAge = Integer.parseInt(strUserAge);

        }

        return userAge;
    }

    // check value for weight entered by user
    public int checkInputWeight()
    {
        int userWeight = 0;
        // get value value from edit text box
        String strUserWeight = userWeightEditTxt.getText().toString();

        // check if string is empty
        if(strUserWeight.isEmpty())
        {
            // no value entered, display error message
            userWeightEditTxt.setError("Please enter your weight");
        }
        else
        {
            // weight is entered has been entered convert string to integer
            userWeight = Integer.parseInt(strUserWeight);
        }

        return userWeight;
    }

    public boolean checkGenderValue()
    {
        // isMALE is set to false, when user clicks on gender switch
        // value will change
        return isMALE;
    }

    public boolean checkPregnantValue()
    {
        return isPREGNANT;
    }

    public String checkInputName()
    {
        // get value from edit text
        String strBuddyName = buddyNameEditTxt.getText().toString();

        if(strBuddyName.isEmpty())
        {
            // no value entered for buddy contact name
            buddyNameEditTxt.setError("Enter name for emergency contact");
        }

        return strBuddyName;
    }

    public int checkInputNumber()
    {
        int intBuddyNumber = 0;
        String strBuddyNumber = buddyNumberEditTxt.getText().toString();

        if(strBuddyNumber.isEmpty())
        {
            buddyNumberEditTxt.setError("Enter number for emergency contact");
        }
        else
        {
            // if number has been entered convert to int
            intBuddyNumber = Integer.parseInt(strBuddyNumber);
        }

        return intBuddyNumber;
    }


    /*
     * test method is used to check if Junit is connecting to this method
     * test cases are stored in following directory
     * /src/androidTest/
     */
    public String testMethod()
    {
        return "This is the test string";
    }

}
