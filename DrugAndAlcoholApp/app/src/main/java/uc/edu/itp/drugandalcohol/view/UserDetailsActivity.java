package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.fragments.UserDetailsFragment;

public class UserDetailsActivity extends FragmentActivity
{
    public UserDetailsFragment userDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        userDetailsFragment = new UserDetailsFragment();

        fragmentTransaction.add(R.id.userDetailsFragmentContainer, userDetailsFragment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;

            case R.id.action_expandable_list:
                loadExampleList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    /*
     * this is example code will remove later
     */
    private void loadExampleList()
    {
        Intent expandableListIntent = new Intent(getApplicationContext(), ExpandableListActivity.class);
        startActivity(expandableListIntent);
    }
}
