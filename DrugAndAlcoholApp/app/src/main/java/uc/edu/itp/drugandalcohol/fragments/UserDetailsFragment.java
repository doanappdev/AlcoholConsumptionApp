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
import android.widget.RelativeLayout;
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
    SharedPreferences userSharedPrefs;

    Button acceptBtn, cancelBtn, editBtn;

    EditText userAgeEditTxt, userWeightEditTxt, buddyNameEditTxt, buddyNumberEditTxt;
    Switch genderSwitch;

    // key values used for shared preferences
    private static final String USER_AGE = "userAgeKey";
    private static final String USER_WEIGHT = "userWeightKey";
    private static final String USER_GENDER = "userGenderKey";
    private static final String USER_PREGNANT = "userPregnantKey";
    private static final String BUDDY_CONTACT_NAME = "buddyNameKey";
    private static final String BUDDY_CONTACT_NUMBER = "buddyNumberKey";

    private boolean gender;



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
        if(genderSwitch.isChecked())
        {
            gender = true;      // male
        }
        else
        {
            gender = false;     // female
        }
    }


    /*
     * Save details entered by user
     */
    private void saveUserPrefs()
    {
        // get values from UI
        int userAge = Integer.parseInt(userAgeEditTxt.getText().toString());
        int userWeight = Integer.parseInt(userWeightEditTxt.getText().toString());
        int buddyNumber = Integer.parseInt(buddyNumberEditTxt.getText().toString());

        String buddyName = buddyNameEditTxt.getText().toString();


        // save values to user shared preferences
        SharedPreferences.Editor editor = userSharedPrefs.edit();
        editor.putInt(USER_AGE, userAge);
        editor.putInt(USER_WEIGHT, userWeight);
        editor.putInt(BUDDY_CONTACT_NUMBER, buddyNumber);
        editor.putString(BUDDY_CONTACT_NAME, buddyName);
        editor.putBoolean(USER_GENDER, gender);
        editor.commit();

        // display toast for testing
        Toast.makeText(getActivity(),  "User Details " + userAge + userWeight, Toast.LENGTH_LONG).show();


    }



}
