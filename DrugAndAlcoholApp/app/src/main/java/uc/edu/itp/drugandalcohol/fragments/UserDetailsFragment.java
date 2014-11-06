package uc.edu.itp.drugandalcohol.fragments;



import android.app.ActionBar;
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

    Button acceptBtn, cancelBtn;

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

        // enable home icon (back  button) on action bar
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setHomeButtonEnabled(true);

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
                // close activity and fragment
                getActivity().finish();
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
                    isPREGNANT = true;      //  user is pregnant

                }
                else
                {
                    isPREGNANT = false;     //  user is not pregnant
                }

                break;

        }

        displayPregnantMsg(isPREGNANT);
    }


    /*
     * Save details entered by user
     */
    private void saveUserPrefs()
    {
        // no need to instantiate UserDetails object because it
        // is a singleton object
        UserDetails.getInstance().setAge(checkInputAge());
        UserDetails.getInstance().setWeight(checkInputWeight());
        UserDetails.getInstance().setGender(getGenderValue());
        UserDetails.getInstance().setPregnant(isPREGNANT);
        UserDetails.getInstance().setStandardDrinks(0);

        buddyContact.setName(checkInputName());
        buddyContact.setNumber(checkInputPhNumber());

        // display toast to confirm details saved (for testing)
        Toast.makeText(getActivity(),  "User Details Saved\nUser Age: " + UserDetails.getInstance().getAge() +
                                       "\nUser Weight: " + UserDetails.getInstance().getWeight() +
                                       "\nMale: " + isMALE +
                                       "\nPregnant: " + isPREGNANT +
                                       "\nBuddy Name: " + buddyContact.getName() +
                                       "\nBuddy Number: " + buddyContact.getNumber(),
                                        Toast.LENGTH_LONG).show();
    }

    /*
     * check the value entered for users age
     * first check if the string is empty,
     * next check if 18 is less than 18
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
            // value for age entered, convert to integer
            userAge = Integer.parseInt(strUserAge);

            // check if age is less than 18
            if(userAge < 18)
            {
                userAgeEditTxt.setError("The legal age to drink is 18");
            }

            if(userAge > 130)
            {
                userAgeEditTxt.setError("I think your lying");
            }

        }

        return userAge;
    }

    // check value for weight entered by user
    public int checkInputWeight()
    {
        int userWeight = 0;
        // get weight value from edit text box
        String strUserWeight = userWeightEditTxt.getText().toString();

        // check if string is empty
        if(strUserWeight.isEmpty())
        {
            // no value entered, display error message
            userWeightEditTxt.setError("Please enter your weight");
        }
        else
        {
            // weight is entered convert string to integer
            userWeight = Integer.parseInt(strUserWeight);
        }

        return userWeight;
    }

    public boolean getGenderValue()
    {
        return isMALE;
    }

    public void displayPregnantMsg(boolean pregnant)
    {
        if(pregnant)
        {
            Toast.makeText(getActivity(), "If you are pregnant it is recommended you do not drink",
                    Toast.LENGTH_LONG).show();
        }

        //return isPREGNANT;
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

    public int checkInputPhNumber()
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

    /*******************************************************************************
     * Testing methods
     *
     ******************************************************************************/


    /*
     * test method is used to check if Junit is connecting to this method
     * test cases are stored in following directory
     * /src/androidTest/
     */
    public String testMethod()
    {
        return "This is the test string";
    }

    public Boolean testSetDetails(String age, String weight, boolean male, boolean pregnant, String budName, String budContact)
    {
        userAgeEditTxt.setText(age);
        userWeightEditTxt.setText(weight);
        //true for male false for female
        isMALE = male;
        isPREGNANT = pregnant;
        buddyNameEditTxt.setText(budName);
        buddyNumberEditTxt.setText(budContact);




        //String[] userDetails = new String[6];
        return acceptBtn.performClick();


    }

    public Boolean testCancelButton()
    {
        return cancelBtn.performClick();
    }

    public int testIncorrectAge(String age)
    {
        userAgeEditTxt.setText(age);
        int checkAge = checkInputAge();
        return checkAge;
    }

    public int testIncorrectWeight(String number)
    {
        userWeightEditTxt.setText(number);
        int checkNumber = checkInputPhNumber();
        return checkNumber;
    }

    public String testIncorrectBuddyName(String buddy)
    {
        buddyNameEditTxt.setText(buddy);
        String name = checkInputName();
        return name;
    }

    public int testIncorrectBuddyContact(String number)
    {
        buddyNumberEditTxt.setText(number);
        int checkNumber = checkInputPhNumber();
        return checkNumber;
    }

}
