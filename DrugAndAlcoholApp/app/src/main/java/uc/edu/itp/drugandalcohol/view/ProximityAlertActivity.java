package uc.edu.itp.drugandalcohol.view;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import uc.edu.itp.drugandalcohol.R;

/*
    ProximityAlertActivity is used to display toast messages when
    user enters or leaves region set as proximity alert
 */
public class ProximityAlertActivity extends Activity
{
    String notificationTitle;
    String notificationContent;
    String tickerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_alert);

        boolean proximity_entering = getIntent().getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);

        if(proximity_entering)
        {
            Toast.makeText(getBaseContext(),"Entering the region"  ,Toast.LENGTH_LONG).show();
            notificationTitle="Proximity - Entry";
            notificationContent="Entered the region";
            tickerMessage = "Entered the region";

        }
        else
        {
            Toast.makeText(getBaseContext(),"Exiting the region"  , Toast.LENGTH_LONG).show();
            notificationTitle="Proximity - Exit";
            notificationContent="Exited the region";
            tickerMessage = "Exited the region";
        }

        Intent notificationIntent = new Intent(getApplicationContext(),NotificationViewActivity.class);
        notificationIntent.putExtra("content", notificationContent );

        /** This is needed to make this intent different from its previous intents */
        notificationIntent.setData(Uri.parse("tel:/" + (int) System.currentTimeMillis()));

        /** Creating different tasks for each notification. See the flag Intent.FLAG_ACTIVITY_NEW_TASK */
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

        /** Getting the System service NotificationManager */
        NotificationManager nManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        /** Configuring notification builder to create a notification */
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setWhen(System.currentTimeMillis())
                .setContentText(notificationContent)
                .setContentTitle(notificationTitle)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setTicker(tickerMessage)
                .setContentIntent(pendingIntent);

        /** creating a notification from the notification builder */


        /** Sending the notification to system.
         * The first argument ensures each notification has a unique id
         * If two notifications share same notification id, then the last notification replaces the first notification
         * */
        nManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());

        /** Finishes the execution of this activity */
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proximity_alert, menu);
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
