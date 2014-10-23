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
    Switch genderSwitch;

    private boolean male, pregnant;



    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_user_details, container, false);

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

        if(genderSwitch.isChecked())
        {
            male = true;      //  user is male

            // hide pregnant switch
            pregnantLayout.setVisibility(LinearLayout.INVISIBLE);
        }
        else
        {
            male = false;     //  user is female

            // display pregnant switch
            pregnantLayout.setVisibility(LinearLayout.VISIBLE);

        }
    }


    /*
     * Save details entered by user
     */
    private void saveUserPrefs()
    {
        int buddyNumInt = 0;
        // get values from UI
        int userAge = Integer.parseInt(userAgeEditTxt.getText().toString());
        int userWeight = Integer.parseInt(userWeightEditTxt.getText().toString());
        String buddyNumString = buddyNumberEditTxt.getText().toString();
        String buddyName = buddyNameEditTxt.getText().toString();


        // check user has entered information
        if(userWeight == 0)
        {
            // display error message
            // weight is needed to calculate BAC
            Toast.makeText(getActivity(),  "Please enter a value for weight", Toast.LENGTH_LONG).show();
        }

        if(buddyName.equals(""))
        {
            // if buddy name equals " " assign no value to name
            buddyName = "No name entered";
        }

        if(buddyNumString.equals(""))
        {
            buddyNumberEditTxt.setError("number not entered");
        }
        else
        {
            // if number has been entered convert to a int
            buddyNumInt = Integer.parseInt(buddyNumString);
        }


        // save values to user shared preferences
        SharedPreferences.Editor editor = userSharedPrefs.edit();
        editor.putInt(getString(R.string.user_age_key), userAge);
        editor.putInt(getString(R.string.user_weight_key), userWeight);
        editor.putInt(getString(R.string.buddy_number_key), buddyNumInt);
        editor.putString(getString(R.string.buddy_name_key), buddyName);
        editor.putBoolean(getString(R.string.user_gender_key), male);

        editor.commit();

        // display toast to confirm details saved
        Toast.makeText(getActivity(),  "User Details:" + userAge + " " + userWeight + " " + male, Toast.LENGTH_LONG).show();


    }

    public String testMethod()
    {
        return "This is the test string";
    }

}
