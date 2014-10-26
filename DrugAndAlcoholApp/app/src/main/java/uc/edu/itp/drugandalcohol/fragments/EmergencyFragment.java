package uc.edu.itp.drugandalcohol.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class EmergencyFragment extends Fragment
{
    private Spinner contactSpinner;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_emergency1, container, false);

        addItemsToSpinner(view);


        // Inflate the layout for this fragment
        return view;
    }

    // add items into spinner dynamically
    public void addItemsToSpinner(View v)
    {
        List<String> contactList = new ArrayList<String>();
        // assign spinner defined in XML to contactSpinner
        contactSpinner = (Spinner)v.findViewById(R.id.spinnerContactNames);

        // create Uri to access contacts (through content provider)
        // stored in users phone
        Uri phoneContacts = Uri.parse("content://contacts/people");

        // create cursor object to access database
        Cursor c = getActivity().managedQuery(phoneContacts, null, null, null, null);

        String[] columns = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        int[] views = new int[] {R.id.spinnerContactNames, R.id.txtViewContactNumber};


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_emergency1, c, columns, views);
        //getActivity().setListAdapter(adapter)

        contactSpinner.setAdapter(adapter);

        /* test code */
        /*
        // add names to spinner
        List<String> contactList = new ArrayList<String>();
        contactList.add("Name 1");
        contactList.add("Name 2");
        contactList.add("Name 3");
        contactList.add("Name 4");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, contactList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactSpinner.setAdapter(dataAdapter);
        */
    }


}
