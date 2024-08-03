package com.example.projecctforandroidlessons;

import android.app.Application;
import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;

public class MApplication extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = AppDatabase.getInstance(getApplicationContext());
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
