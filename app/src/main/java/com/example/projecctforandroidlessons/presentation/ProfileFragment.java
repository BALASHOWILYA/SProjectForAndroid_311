package com.example.projecctforandroidlessons.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projecctforandroidlessons.R;

public class ProfileFragment extends Fragment {

    private static final String DEBUG_TAG = "Gestures";
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    private ViewGroup rootView;
    private TextView userNameTextView;
    private ImageView imageView;

    private float scaleFactor = 1.0f;

    private void init(View view) {
        rootView = view.findViewById(R.id.root_view); // Ensure you have a root view with this ID in your layout
        userNameTextView = view.findViewById(R.id.userNameTextView);
        imageView = view.findViewById(R.id.image_id);

        if (rootView != null) {
            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    scaleGestureDetector.onTouchEvent(event);
                    gestureDetector.onTouchEvent(event);
                    return true;
                }
            });
        }

        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scaleFactor *= detector.getScaleFactor();
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 3.0f));
                applyScale();
                Log.d(DEBUG_TAG, "onScale: " + detector.getScaleFactor() + " scaleFactor: " + scaleFactor);
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                Log.d(DEBUG_TAG, "onScaleBegin: " + detector.getScaleFactor());
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                Log.d(DEBUG_TAG, "onScaleEnd: " + detector.getScaleFactor());
            }
        });

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (scaleFactor < 2.0f) {
                    scaleFactor = 2.0f;
                } else {
                    scaleFactor = 1.0f;
                }
                applyScale();
                Log.d(DEBUG_TAG, "onDoubleTap: scaleFactor: " + scaleFactor);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(DEBUG_TAG, "onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d(DEBUG_TAG, "onFling");
                return true;
            }
        });
    }

    private void applyScale() {
        userNameTextView.setScaleX(scaleFactor);
        userNameTextView.setScaleY(scaleFactor);
        imageView.setScaleX(scaleFactor);
        imageView.setScaleY(scaleFactor);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUserName();
    }

    private void updateUserName() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String userName = sharedPreferences.getString("edit_pref", ""); // "edit_pref" is the key for EditTextPreference
        if (userNameTextView != null) {
            userNameTextView.setText(userName);
        }
    }
}
