package uc.edu.itp.drugandalcohol.fragments;



import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uc.edu.itp.drugandalcohol.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TabBeerFragment extends Fragment
{
    RelativeLayout relativeLayoutBeer;
    TextView beer1InputTxtView, beer2InputTxtView, beer3InputTxtView, beer4InputTxtView;

    public TabBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // inflate the view with RelativeLayout so we can have functionality with buttons etc..
        relativeLayoutBeer = (RelativeLayout)inflater.inflate(R.layout.fragment_tab_beer, container, false);

        beer1InputTxtView = (TextView) relativeLayoutBeer.findViewById(R.id.txtViewBeerInput1);
        beer1InputTxtView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // for debugging
                Log.d(getClass().getCanonicalName(), "TextView was clicked!");
                // display toast to check if click event works
                // must call getActivity() when calling Toast from fragment
                //Toast.makeText(getActivity(), "Beer Input TextView click event works", Toast.LENGTH_LONG).show();

                showNumberPad();
            }
        });

        // Inflate the layout for this fragment
        return relativeLayoutBeer;
    }

    void showNumberPad()
    {
        //NumberPadFragment numberPad = (NumberPadFragment)getFragmentManager().findFragmentById(R.id.f)

        FragmentManager fm = getFragmentManager();
        NumberPadFragment numberPadFragment = new NumberPadFragment();
        numberPadFragment.show(fm, "Alcohol Consumption");
    }


}
