package com.example.projecctforandroidlessons;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VideoFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button btnPlay, btnPause, btnStop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Привязываем макет фрагмента
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Инициализация SurfaceView и кнопок
        surfaceView = view.findViewById(R.id.surface_view_id);
        btnPlay = view.findViewById(R.id.btn_play_id);
        btnPause = view.findViewById(R.id.btn_pause_id);
        btnStop = view.findViewById(R.id.btn_stop_id);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initializeMediaPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });

        // Устанавливаем обработчики для кнопок
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(requireContext(), "Video resumed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    Toast.makeText(requireContext(), "Video paused", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    initializeMediaPlayer(); // Повторная инициализация для возможности перезапуска
                    Toast.makeText(requireContext(), "Video stopped", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    // Метод для инициализации и подготовки MediaPlayer
    private void initializeMediaPlayer() {
        try {
            Uri videoUri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.sample_video);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(requireContext(), videoUri);
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.prepare();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Видео готово к воспроизведению
                    Toast.makeText(requireContext(), "Video prepared", Toast.LENGTH_SHORT).show();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(requireContext(), "Video completed", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error initializing video", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
