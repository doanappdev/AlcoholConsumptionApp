package uc.edu.itp.drugandalcohol.fragments;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import uc.edu.itp.drugandalcohol.MainActivity;
import uc.edu.itp.drugandalcohol.R;

/**
 * Emergency Fragment
 * this fragment loads contact details from users phone into spinner
 * and list objects. It also implements click listeners for various
 * views. User can send emergency SMS from this UI
 */
public class EmergencyFragment extends Fragment
        implements View.OnClickListener, AdapterView.OnItemSelectedListener
{


    private Spinner contactSpinner, standardMsgSpinner;
    private EditText customMsgEditTxt, contactNumberEditTxt;
    private Button sendBtn, cancelBtn;

    private List<String> contactNameList = new ArrayList<String>();
    private List<String> contactNumberList = new ArrayList<String>();

    private boolean showSpinner = false;
    private boolean showEditText = false;
    private boolean showPhNumEditTxt = false;

    private String selectedName, selectedMsg, selectedPhNum;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_emergency1, container, false);

        LinearLayout lLayoutStandardMsg, lLayoutCustomMsg, lLayoutContactNumber;

        // assign layouts and views defined in XML files
        lLayoutStandardMsg = (LinearLayout)view.findViewById(R.id.lLayoutContactStandardMsg);
        lLayoutCustomMsg = (LinearLayout)view.findViewById(R.id.lLayoutContactCustomMsg);
        lLayoutContactNumber = (LinearLayout)view.findViewById(R.id.lLayoutContactNumber);

        customMsgEditTxt = (EditText)view.findViewById(R.id.editTxtContactCustomMsg);
        contactNumberEditTxt = (EditText)view.findViewById(R.id.editTxtContactNumber);
        contactSpinner = (Spinner)view.findViewById(R.id.spinnerContactNames);
        standardMsgSpinner = (Spinner)view.findViewById(R.id.spinnerStandardMsg);
        sendBtn = (Button)view.findViewById(R.id.btnSend);
        cancelBtn = (Button)view.findViewById(R.id.btnCancel);

        // attach click listener to buttons and spinners
        sendBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        contactSpinner.setOnItemSelectedListener(this);
        standardMsgSpinner.setOnItemSelectedListener(this);

        // add click event for linear layout
        setClickEventStandardMsg(lLayoutStandardMsg);
        setClickEventCustomMsg(lLayoutCustomMsg);
        setClickEventContactNumber(lLayoutContactNumber);

        addItemsToSpinner();

        // enable home icon on action bar
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setHomeButtonEnabled(true);

        // Inflate the layout for this fragment
        return view;
    }

    // add items into spinner dynamically
    public void addItemsToSpinner()
    {
        // load contacts into cursor
        Cursor cursor = getContactsFromPhone();

        // loop through cursor to add contact names and number to list
        while(cursor.moveToNext())
        {
            contactNameList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
            contactNumberList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }

        // create adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, contactNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactSpinner.setAdapter(dataAdapter);

    }

    /*
     * return cursor object with phone details
     */
    private Cursor getContactsFromPhone()
    {
        // create Uri to access contacts
        // stored in users phone (through content provider)
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // array to specify column names in content provider
        String[] projection = new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // set parameters for content resolver
        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?";
        String[] selectionArgs = new String[] { String.valueOf(1)};
        // sort contacts in ascending order
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return getActivity().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private void setClickEventStandardMsg(LinearLayout ll)
    {
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(!showSpinner)
                    {
                        // show standard msg spinner
                        standardMsgSpinner.setVisibility(LinearLayout.VISIBLE);
                        showSpinner = true;
                    }
                    else
                    {
                        // hide standard msg spinner
                        standardMsgSpinner.setVisibility(LinearLayout.GONE);
                        showSpinner = false;
                    }


                }
                return false;
            }
        });
    }

    private void setClickEventCustomMsg(LinearLayout ll)
    {
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(!showEditText)
                    {
                        // show standard msg spinner
                        customMsgEditTxt.setVisibility(EditText.VISIBLE);
                        showEditText = true;
                    }
                    else
                    {
                        // hide standard msg spinner
                        customMsgEditTxt.setVisibility(EditText.GONE);
                        showEditText = false;
                    }


                }
                return false;
            }
        });
    }

    private void setClickEventContactNumber(LinearLayout ll)
    {
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(!showPhNumEditTxt)
                    {
                        // show standard msg spinner
                        contactNumberEditTxt.setVisibility(EditText.VISIBLE);
                        showPhNumEditTxt = true;
                    }
                    else
                    {
                        // hide standard msg spinner
                        contactNumberEditTxt.setVisibility(EditText.GONE);
                        showPhNumEditTxt = false;
                    }
                }

                return false;
            }
        });
    }

    // click listener for buttons
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnSend:
                getMsgDetails();
                //sendEmergencySMS();
                break;

            case R.id.btnCancel:

                break;
        }
    }

    /*
     * spinner click listener
     * assign the selected item on spinner to variables
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        switch(parent.getId())
        {
            case R.id.spinnerContactNames:
                // get selected name from spinner
                selectedName = parent.getItemAtPosition(pos).toString();

                // display number linked to name in edit text
                contactNumberEditTxt.setText(contactNumberList.get(pos));
                // get Ph. number from list, using pos on spinner
                selectedPhNum = contactNumberList.get(pos);

                contactNumberEditTxt.setVisibility(EditText.VISIBLE);
                showPhNumEditTxt = true;
                Toast.makeText(getActivity(),
                                "Name: " + selectedName +
                                "\nNumber: " + selectedPhNum +
                                "\nPos: " + pos,
                        Toast.LENGTH_LONG).show();
                break;

            case R.id.spinnerStandardMsg:
                // get standard message from spinner
                selectedMsg = parent.getItemAtPosition(pos).toString();
        }
    }

    /*
     * this method is required when using onItemSelectedListener
     */
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void getMsgDetails()
    {
        String buddyName, buddyNumber, message;

        buddyName = String.valueOf(contactSpinner.getSelectedItem());
        buddyNumber = contactNumberList.get(contactSpinner.getSelectedItemPosition());
        message = String.valueOf(standardMsgSpinner.getSelectedItem());

        sendEmergencySMS(buddyName, buddyNumber, message);

        /*
        // testing
        Toast.makeText(getActivity(),
                "SMS Details\nName: " + buddyName  +
                "\nNumber: " + buddyNumber +
                "\nStandard Msg: " + message  ,
                Toast.LENGTH_LONG).show();
        */
    }

    private void sendEmergencySMS(String name, String number, String msg)
    {
        try
        {
            // send message with out starting built in sms app
            SmsManager smsManager = SmsManager.getDefault();
            /*
             * send text message - for testing use a phone number
             * e.g. String number1 = "0451683962";
             * @param:
             *      destination address
             *      source address
             *      text message
             *      destination intent
             *      source intent
             */

            // un-comment this code when ready to test with other numbers
            //smsManager.sendTextMessage(number, null, msg, null, null);

            // supply known number
            String number1 = "0451683962"; // Eric's mobile number
            smsManager.sendTextMessage(number1, null, msg, null, null);

            /*
            // testing values for message
            Toast.makeText(getActivity(),
                    "Your message has been sent to\nName: " + name +
                            "\nNumber: " + number1 +
                            "\nText: " + msg,
                    Toast.LENGTH_LONG).show();
            */
        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    /*
     * click listener for icons on action bar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_send_sms:
                // get message details and send sms
                getMsgDetails();
                break;

            // id for home icon on action bar
            case android.R.id.home:
                // create intent to start main menu
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public String getSelectNumber(int pos)
    {
        String[] testNumber = getResources().getStringArray(R.array.Number_List);
        String thisNumber = testNumber[pos];

        return thisNumber;
    }

    public String getSelectedContact(int pos)
    {
        String[] testContacts = getResources().getStringArray(R.array.Contact_list);
        String thisContact = testContacts[pos];

        return thisContact;
    }

    /*******************************************************************************
     * Testing methods
     *
     ******************************************************************************/
}
