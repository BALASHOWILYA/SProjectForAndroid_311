package com.example.projecctforandroidlessons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(
                Intent.ACTION_TIME_CHANGED.equals(action) ||
                Intent.ACTION_DATE_CHANGED.equals(action) ||
                Intent.ACTION_TIMEZONE_CHANGED.equals(action)
                )  {
            Log.d("TimeChanged", "Time or Date has been changed");
        }
    }
}