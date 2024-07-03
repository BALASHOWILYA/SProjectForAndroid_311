package com.example.projecctforandroidlessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class HomeFragment extends Fragment {

    private ImageView image;
    private Button blinkButton, fadeButton,
            moveButton, rotateButton, slideButton, zoomButton;

    private void init(View view){
        blinkButton = view.findViewById(R.id.blink_button_id);
        fadeButton = view.findViewById(R.id.fade_button_id);
        moveButton = view.findViewById(R.id.move_button_id);
        rotateButton = view.findViewById(R.id.rotate_button_id);
        slideButton = view.findViewById(R.id.slide_button_id);
        zoomButton = view.findViewById(R.id.zoom_button_id);
        image = view.findViewById(R.id.image_id);

    }

    private void createAnimation(Button button, int animation_id){
        button.setOnClickListener( v->{
            Animation animation =  AnimationUtils.loadAnimation(getContext(),animation_id) ;
            image.startAnimation(animation);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        createAnimation(blinkButton, R.anim.blink_animation);
        createAnimation(fadeButton, R.anim.fade_animation);
        createAnimation(moveButton, R.anim.move_animation);
        createAnimation(rotateButton, R.anim.rotate_animation);
        createAnimation(zoomButton, R.anim.zoom_animation);
        createAnimation(slideButton, R.anim.slide_animation);

        // Inflate the layout for this fragment
        return view;
    }


}