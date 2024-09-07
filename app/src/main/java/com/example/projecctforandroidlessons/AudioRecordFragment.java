package com.example.projecctforandroidlessons;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class AudioRecordFragment extends Fragment {

    private static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private Button btnStartRecording, btnStopRecording, btnPlayRecording;;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_audio_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        // Проверяем разрешения при запуске
        if (!checkPermissions()) {
            requestPermissions(); // Если разрешения не предоставлены, запрашиваем их
        }

        // Обработка нажатия кнопки для начала записи
        btnStartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    // Если разрешения получены, запускаем сервис для записи
                    startRecordingService();
                } else {
                    // Запрашиваем разрешения, если они не предоставлены
                    requestPermissions();
                }
            }
        });

        // Обработка нажатия кнопки для остановки записи
        btnStopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecordingService();
            }
        });

    }

    private void init(View view) {
        // Инициализация кнопок
        btnStartRecording = view.findViewById(R.id.btnStartRecording);
        btnStopRecording = view.findViewById(R.id.btnStopRecording);
        btnPlayRecording = view.findViewById(R.id.btnPlayRecording);
    }

    private void playRecording() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            // Путь к файлу во внутреннем хранилище
            String filePath = getActivity().getFilesDir().getAbsolutePath() + "/audiorecordtest.3gp";
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(getActivity(), "Playing recording", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("AudioRecordFragment", "Error playing recording: " + e.getMessage());
            Toast.makeText(getActivity(), "Playback failed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermissions() {
        int audioResult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        return audioResult == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        requestPermissions(new String[]{
                Manifest.permission.RECORD_AUDIO,
        }, REQUEST_AUDIO_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                boolean audioPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (audioPermissionGranted) {
                    // Разрешения получены, можно начать запись
                    Toast.makeText(getActivity(), "Audio Permission Granted", Toast.LENGTH_SHORT).show();
                    startRecordingService();
                } else {
                    // Разрешения не получены, выводим сообщение
                    Toast.makeText(getActivity(), "Audio Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void startRecordingService() {
        Intent intent = new Intent(getActivity(), AudioRecordService.class);
        String filePath = getActivity().getFilesDir().getAbsolutePath() + "/audiorecordtest.3gp";
        intent.putExtra("filePath", filePath);
        getActivity().startService(intent);
    }

    // Метод для остановки сервиса записи
    private void stopRecordingService() {
        Intent intent = new Intent(getActivity(), AudioRecordService.class);
        getActivity().stopService(intent);
    }
}