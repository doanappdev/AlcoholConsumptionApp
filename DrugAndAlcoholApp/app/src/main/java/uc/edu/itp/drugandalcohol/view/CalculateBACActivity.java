package uc.edu.itp.drugandalcohol.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.controller.TabListener;
import uc.edu.itp.drugandalcohol.fragments.TabBeerFragment;
import uc.edu.itp.drugandalcohol.fragments.TabSpiritsFragment;
import uc.edu.itp.drugandalcohol.fragments.TabWineFragment;

public class CalculateBACActivity extends Activity
{
    // Declare Tab variable
    ActionBar.Tab beerTab, wineTab, spiritsTab;
    Fragment fragmentBeer = new TabBeerFragment();
    Fragment fragmentWine = new TabWineFragment();
    Fragment fragmentSpirits = new TabSpiritsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_calculate);

        ActionBar actionBar = getActionBar();

        // hide actionbar icon
        actionBar.setDisplayHomeAsUpEnabled(false);

        // hide actionbar title
        actionBar.setDisplayShowTitleEnabled(false);

        // create actionbar tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // set tab icon and titles
        // .setIcon(R.drawable.beer_glass_icon)
        // .setIcon(R.drawable.wine_glass_icon)
        // .setIcon(R.drawable.spirits_48)
        beerTab = actionBar.newTab().setText("Beer");

        wineTab = actionBar.newTab().setText("Wine");

        spiritsTab = actionBar.newTab().setText("Spirits");

        // set tab listeners
        beerTab.setTabListener(new TabListener(fragmentBeer));
        wineTab.setTabListener(new TabListener(fragmentWine));
        spiritsTab.setTabListener(new TabListener(fragmentSpirits));

        // add tabs to action bar
        actionBar.addTab(beerTab);
        actionBar.addTab(wineTab);
        actionBar.addTab(spiritsTab);

    }


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
}
