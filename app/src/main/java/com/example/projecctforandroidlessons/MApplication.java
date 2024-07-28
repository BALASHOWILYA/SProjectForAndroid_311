package com.example.projecctforandroidlessons;

import android.app.Application;

import com.example.projecctforandroidlessons.data.roomdb.AppDatabase;

public class MApplication extends Application {

    private static MApplication instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = AppDatabase.getInstance(this);
    }

    public static synchronized MApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
