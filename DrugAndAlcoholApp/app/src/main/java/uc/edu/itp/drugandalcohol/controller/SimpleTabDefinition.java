package uc.edu.itp.drugandalcohol.controller;

import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.controller.TabDefinition;

/**
 * Created by AppDev on 1/10/2014.
 * This class extends TabDefinition (which is an abstract class)
 */
public class SimpleTabDefinition extends TabDefinition
{
    private final static String TAG = "SimpleTabDefinition";
    //
    // Fields
    //
    private final int _tabTitleResourceId;
    private final int _tabTitleViewId;
    private final int _tabLayoutId;
    private final Fragment _fragment;

    //
    // Constructors
    //
    /**
     * The constructor for {@link SimpleTabDefinition}.
     * @param tabContentViewId The layout ID of the contents to use when the tab is active.
     * @param tabLayoutId The ID of the layout to use when inflating the tab {@link View}.
     * @param tabTitleResourceId The string resource ID for the title of the tab.
     * @param tabTitleViewId The layout ID for the title of the tab.
     * @param fragment The {@link Fragment} used when the tab is active.
     */
    public SimpleTabDefinition(int tabContentViewId, int tabLayoutId,
                               int tabTitleResourceId, int tabTitleViewId, Fragment fragment) {
        super(tabContentViewId);

        _tabLayoutId = tabLayoutId;
        _tabTitleResourceId = tabTitleResourceId;
        _tabTitleViewId = tabTitleViewId;
        _fragment = fragment;
    }

    //
    // Exposed Members
    //
    @Override
    public Fragment getFragment() {
        return _fragment;
    }

    @Override
    public View createTabView(LayoutInflater inflater, ViewGroup tabsView) {
        // we need to inflate the view based on the layout id specified when
        // this instance was created.
        View indicator = inflater.inflate(
                _tabLayoutId,
                tabsView,
                false);

        // set up the title of the tab. this will populate the text view with the
        // string value of _tabTitleResourceId passed as a parameter when this instance is
        // created. the text will also be centered within the title control.
        TextView titleView = (TextView)indicator.findViewById(_tabTitleViewId);
        titleView.setText(_tabTitleResourceId);
        titleView.setGravity(Gravity.CENTER);

        // We can set the tab focus to the Beer tab
        // first check if the string value equals '2131296262'
        // which is the int value of "Beer" in the strings.xml file
        // if true we set the text view background color
        //if(_tabTitleResourceId == 2131296262)   // number for beer tab title in strings.xml file
       // {
        //    titleView.setTextColor(R.color.white1);
            // R.color.md_orange_300 = 2131034152
        //    titleView.setBackgroundColor(2131034152);
        //    titleView.setFocusable(true);
        //    Log.d(TAG, "String equals " + titleView.getText());
        //}

        //Log.i(TAG, "Resource ID: " + _tabTitleResourceId);
        //Log.i(TAG, "Color value (md_orange_500): " + R.color.md_orange_500);


        // ensure the control we're inflating is layed out properly. this will
        // cause our tab titles to be placed evenly weighted across the top.
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        indicator.setLayoutParams(layoutParams);

        return indicator;
    }
}
