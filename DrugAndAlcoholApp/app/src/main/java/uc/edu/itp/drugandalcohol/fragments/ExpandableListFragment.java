package uc.edu.itp.drugandalcohol.fragments;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uc.edu.itp.drugandalcohol.R;
import uc.edu.itp.drugandalcohol.controller.ExpandableListAdapter;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ExpandableListFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public ExpandableListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_expandable_list, container, false);
        expListView = (ExpandableListView)v.findViewById(R.id.expListView);

        // prepare data for list
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);


        // Inflate the layout for this fragment
        return v;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Beer");
        listDataHeader.add("Wine");
        listDataHeader.add("Spirits");

        // Adding child data
        List<String> beer = new ArrayList<String>();
        beer.add("Beer Full Strength");
        beer.add("Beer Glass Small");
        beer.add("Beer Glass Large");


        List<String> wine = new ArrayList<String>();
        wine.add("Wine1");
        wine.add("Wine2");
        wine.add("Wine3");
        wine.add("Wine4");

        List<String> spirits = new ArrayList<String>();
        spirits.add("Spirits1");
        spirits.add("Spirits2");
        spirits.add("Spirits3");
        spirits.add("Spirits4");


        listDataChild.put(listDataHeader.get(0), beer); // Header, Child data
        listDataChild.put(listDataHeader.get(1), wine);
        listDataChild.put(listDataHeader.get(2), spirits);
    }


}
