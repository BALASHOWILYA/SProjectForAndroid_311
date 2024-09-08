package com.example.projecctforandroidlessons;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class SensorService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private  Sensor accelerometer;

    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accelerometer != null){

                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            }
        }

        Log.d("SensorService", "Service Started");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("SensorService", "Gyroscope X: " + x + "Y: " + y + "Z: " + z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this);
        Log.d("SensorService", "Service stopped");
    }
}