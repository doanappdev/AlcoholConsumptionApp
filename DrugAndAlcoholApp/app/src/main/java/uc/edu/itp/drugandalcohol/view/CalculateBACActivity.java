package uc.edu.itp.drugandalcohol.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.controller.TabListener;
import uc.edu.itp.drugandalcohol.fragments.CalculateBACBottomFragment;
import uc.edu.itp.drugandalcohol.fragments.TabBeerFragment;
import uc.edu.itp.drugandalcohol.fragments.TabSpiritsFragment;
import uc.edu.itp.drugandalcohol.fragments.TabWineFragment;

public class CalculateBACActivity extends FragmentActivity
{

    // Declare Tab Variables
    // make these public so we can access them in test class
    public ActionBar actionBar;
    public ActionBar.Tab Tab1, Tab2, Tab3;

    public TabBeerFragment beerTabFragment = new TabBeerFragment();
    public TabWineFragment wineTabFragment = new TabWineFragment();
    public TabSpiritsFragment spiritsFragment = new TabSpiritsFragment();

    // only need to create this object for testing,
    // do not need it to create UI
    public CalculateBACBottomFragment calculateBACBottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bac);

        // get reference to action bar
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // create action bar tabs
        //actionBar.setDisplayShowTitleEnabled(false);    // hide action bar title
        //actionBar.setDisplayShowHomeEnabled(false);     // hide action bar title

        // set tab color to colors defined in actionbar_tab_indicator
        actionBar.setStackedBackgroundDrawable(getResources()
                .getDrawable(R.drawable.actionbar_tab_indicator));


        // set tab icon and titles
        Tab1 = actionBar.newTab().setText("Beer");
        Tab2 = actionBar.newTab().setText("Wine");
        Tab3 = actionBar.newTab().setText("Spirits");

        // set tab listeners
        Tab1.setTabListener(new TabListener(beerTabFragment));
        Tab2.setTabListener(new TabListener(wineTabFragment));
        Tab3.setTabListener(new TabListener(spiritsFragment));

        // add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        actionBar.addTab(Tab3);

        // instantiate object so testing class can access the bottom fragement
        // if we remove this line the calculateBAC UI will still display the
        // bottom fragment as the xml file for this activity will load the
        // fragment
        calculateBACBottomFragment = new CalculateBACBottomFragment();


    }


    /*
        commenting out so menu icon does not show on action bar
        if you want to add menu options for activity remove comments
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.consumption, menu);
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
    */
}
