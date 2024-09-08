package com.example.projecctforandroidlessons;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class SecondSencerService extends Service implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magneticFieldSensor;
    private float[] valuesAccel = new float[3];
    private float[] valuesMagnet = new float[3];
    private float[] valuesResult = new float[3];
    private float[] valuesResult2 = new float[3];


    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return  START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            System.arraycopy(event.values, 0, valuesAccel, 0, valuesAccel.length);
        }
        else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            System.arraycopy(event.values, 0, valuesMagnet, 0, valuesMagnet.length);
        }

        getDiviceOrientation();
        getActualDeviceOrientation();

        sendSensorData();
    }

    private void sendSensorData() {
    }

    private void getActualDeviceOrientation() {
    }


    private void getDiviceOrientation() {
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}