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
import java.util.ResourceBundle;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class EmergencyFragment extends Fragment
{
    private Spinner contactSpinner;

    private List<String> contactNameList = new ArrayList<String>();
    private List<String> contactNumberList = new ArrayList<String>();

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
        // assign spinner defined in XML to contactSpinner
        contactSpinner = (Spinner)v.findViewById(R.id.spinnerContactNames);

        // load contacts into cursor
        Cursor cursor = getContactsFromPhone();

        // loop through cursor to add contact names to spinner
        while(cursor.moveToNext())
        {
            contactNameList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
            contactNumberList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, contactNameList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactSpinner.setAdapter(dataAdapter);

        /* test code - how to add data to spinner */
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

    /*
     * return a cursor objects with phone details
     */
    private Cursor getContactsFromPhone()
    {
        // create Uri to access contacts (through content provider)
        // stored in users phone
        //Uri phoneContacts = Uri.parse("content://contacts/people");
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // array to hold contact data
        String[] projection = new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // set the selection and sortOrder parameters for content resolver
        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?";

        String[] selectionArgs = new String[] { String.valueOf(1)};

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return getActivity().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }



}
