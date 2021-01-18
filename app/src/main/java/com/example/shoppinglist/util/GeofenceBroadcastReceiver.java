package com.example.shoppinglist.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.shoppinglist.act.FavShopsMapActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Notifs notifs = new Notifs(context);

        GeofencingEvent event = GeofencingEvent.fromIntent(intent);

        switch(event.getGeofenceTransition()) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                notifs.send("GEOFENCE_ENTER", "", FavShopsMapActivity.class, NotificationCompat.PRIORITY_HIGH);
                Toast.makeText(context, "ENTER", Toast.LENGTH_SHORT).show();
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "DWELL", Toast.LENGTH_SHORT).show();
                notifs.send("GEOFENCE_DWELL", "", FavShopsMapActivity.class, NotificationCompat.PRIORITY_DEFAULT);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "EXIT", Toast.LENGTH_SHORT).show();
                notifs.send("GEOFENCE_EXIT", "", FavShopsMapActivity.class, NotificationCompat.PRIORITY_HIGH);
                break;
        }
    }
}
