package uc.edu.itp.drugandalcohol.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import uc.edu.itp.drugandalcohol.model.LocationData;

/**
 * Created by AppDeveloper on 24/10/2014.
 */
public class LocationDataAdapter extends ArrayAdapter<LocationData>
{
    /**
     * Adapter context
     */
    Context mContext;

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    public LocationDataAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }



}
