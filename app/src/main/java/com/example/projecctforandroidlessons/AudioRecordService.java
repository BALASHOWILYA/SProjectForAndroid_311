package com.example.projecctforandroidlessons;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class AudioRecordService extends Service {

    private static final String TAG = "AudioRecordService";
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;  // Флаг для отслеживания состояния записи
    private String filePath;  // Путь для сохранения файла

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
    }

    // Настройка MediaRecorder для записи
    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        // Сохраняем файл во внутреннем хранилище приложения
        mediaRecorder.setOutputFile(filePath);
    }



    // Запуск записи
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra("filePath")) {
            filePath = intent.getStringExtra("filePath");
        } else {
            // Если путь не указан, используем внутреннее хранилище по умолчанию
            filePath = getFilesDir().getAbsolutePath() + "/audiorecordtest.3gp";
        }

        if (!isRecording) {  // Проверяем, что запись не ведется
            setupMediaRecorder();
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
                isRecording = true;  // Устанавливаем флаг
                Log.d(TAG, "Recording started, file saved to: " + filePath);
            } catch (IOException e) {
                Log.e(TAG, "MediaRecorder prepare failed: " + e.getMessage());
            } catch (IllegalStateException e) {
                Log.e(TAG, "MediaRecorder start failed: " + e.getMessage());
            }
        } else {
            Log.d(TAG, "Recording already in progress");
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        if (isRecording) {
            try {
                mediaRecorder.stop();
                isRecording = false;  // Сбрасываем флаг после успешной остановки записи
                Log.d(TAG, "Recording stopped, file saved to: " + filePath);
            } catch (IllegalStateException e) {
                Log.e(TAG, "Error stopping MediaRecorder: " + e.getMessage());
            } finally {
                mediaRecorder.reset();  // Сбрасываем MediaRecorder
                mediaRecorder.release();
                mediaRecorder = null;
            }
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  // Этот сервис не поддерживает привязку
    }
}