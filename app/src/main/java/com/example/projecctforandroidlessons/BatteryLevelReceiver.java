package com.example.projecctforandroidlessons;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projecctforandroidlessons.presentation.MainActivity;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "battery_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if (scale > 0) {
            float batteryPct = level * 100 / (float) scale;
            Log.d("BatteryLevel", "battery level: " + batteryPct + "%");
            //sendNotification(context, batteryPct);
            if (batteryPct < 20) {
                Log.d("BatteryLevel", "battery is low");

            }
        } else {
            Log.d("BatteryLevel", "unable to determine battery level");
        }

    }

    @SuppressLint("MissingPermission")
    private void sendNotification(Context context, float batteryPct) {
        createNotification(context);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Low Battery Warning")
                .setContentText("Battery is " + batteryPct + "%")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1, notification);
    }


    private void createNotification(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Battery channel";
            String description = "Channel for battery level notification";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}