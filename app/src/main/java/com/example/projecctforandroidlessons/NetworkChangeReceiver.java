package com.example.projecctforandroidlessons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            boolean isWife = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean isMobile = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;

            if(isWife){
                Log.d("Network", "Connected to Wife" );
            }
            else if (isMobile){
                Log.d("Network", "Connected to Mobile Wife");
            }
            else{
                Log.d("Network", "Connected to other network");
            }
        } else {
            Log.d("Network", "No network connection");
        }

    }
}