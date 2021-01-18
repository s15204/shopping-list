package com.example.shoppinglist.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Notifs extends ContextWrapper {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notifs(Context base) {
        super(base);
        NotificationChannel notificationChannel = new NotificationChannel("com.example.shoppinglist.notifchannel", "notifchannel", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("shopping app notification channel");
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);
    }

    public void send(String title, String body, Class activity, int priority) {
        Intent intent = new Intent(this, activity);
        PendingIntent pIntent = PendingIntent.getActivity(this, 125, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat
                .Builder(this, "com.example.shoppinglist.notifchannel")
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(priority)
                .setStyle(new NotificationCompat.BigTextStyle().setSummaryText("").setBigContentTitle(title).bigText(body))
                .setAutoCancel(true)
                .setContentIntent(pIntent)
                .build();
        NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);
    }
}
