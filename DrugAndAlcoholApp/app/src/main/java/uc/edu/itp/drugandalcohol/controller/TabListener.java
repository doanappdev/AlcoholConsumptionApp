package uc.edu.itp.drugandalcohol.controller;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

import uc.edu.itp.drugandalcohol.R;

/**
 * This tab listener class manages the selected tab clicks to show
 * or remove a fragment
 */
public class TabListener implements ActionBar.TabListener
{
    Fragment mFragment;

    public TabListener(Fragment fragment)
    {
        this.mFragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        //ft.replace(R.id.fragment_calculate_container, mFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        ft.remove(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {


    }

}
