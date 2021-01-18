package com.example.shoppinglist.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

public class Geofencer extends ContextWrapper {

    private PendingIntent pIntent;

    public Geofencer(Context base) {
        super(base);
    }

    public PendingIntent getPendingIntent() {
        if (pIntent == null) {
            Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
            pIntent = PendingIntent.getBroadcast(this, 126, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return pIntent;
    }

    public Geofence getGeofence(String id, LatLng latLng, float radius, int transitionTypes) {
        return new Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(latLng.latitude,latLng.longitude, radius)
                .setTransitionTypes(transitionTypes)
                .setLoiteringDelay(5000)
                .build();
    }


}
