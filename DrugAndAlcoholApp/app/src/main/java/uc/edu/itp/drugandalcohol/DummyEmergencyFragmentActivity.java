package uc.edu.itp.drugandalcohol;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import uc.edu.itp.drugandalcohol.fragments.EmergencyFragment;

/**
 * Created by Robyn on 29/10/2014.
 */
public class DummyEmergencyFragmentActivity extends FragmentActivity {

    EmergencyFragment emergencyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        emergencyFragment = new EmergencyFragment();

        fragmentTransaction.add(R.id.fragmentEmergency, emergencyFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.unit_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addItemsToSpinner()
    {
        emergencyFragment.addItemsToSpinner();
    }

    public void onOptionsItemSelected()
    {
       // emergencyFragment.onOptionsItemSelected();
    }
}
