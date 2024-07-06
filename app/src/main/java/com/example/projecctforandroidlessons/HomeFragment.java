package com.example.projecctforandroidlessons;

import android.annotation.SuppressLint;
import android.icu.number.Scale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class HomeFragment extends Fragment implements View.OnTouchListener {



    private float startX,  startY;
    private int imageWidth, imageHeight;
    private float offsetX, offsetY;
    private boolean isDragging = false;


    private ScaleGestureDetector scaleGestureDetector;

    private LinearLayout linearLayout = null;
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
        linearLayout = view.findViewById(R.id.home_layout_id);

    }



    private  void setupScaleGestureDetector(){
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector){
                float scaleFactor = detector.getScaleFactor();
                Log.d("ScaleGesture", "OnScale");
                return  true;

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addTouchListener(ViewGroup viewGroup){
        viewGroup.setOnTouchListener((v, event) ->{
            switch (event.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    Log.d("TouchEvent", "Action down at (" + event.getX() + "," + event.getY() + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPressed));
                    return true;
                case MotionEvent.ACTION_UP:
                    Log.d("TouchEvent", "Action up at (" + event.getX() + "," + event.getY()  + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                    return true;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.d("TouchEvent", "Action pointer down at (" + event.getX() + "," + event.getY() + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkDark));
                    return true;
                case MotionEvent.ACTION_MOVE:
                    Log.d("TouchEvent", "Action move at (" + event.getX() + "," + event.getY() + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    return true;
                case MotionEvent.ACTION_POINTER_UP:
                    Log.d("TouchEvent", "Action pointer up at (" + event.getX() + "," + event.getY() + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkDark));
                    return true;
                case MotionEvent.ACTION_CANCEL:
                    Log.d("TouchEvent", "Action cansel up at (" + event.getX() + "," + event.getY() + ")");
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    return true;
                default:
                    return  false;



            }
        });
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
        image.setOnTouchListener(this);
        addTouchListener(linearLayout);

        setupScaleGestureDetector();

        createAnimation(blinkButton, R.anim.blink_animation);
        createAnimation(fadeButton, R.anim.fade_animation);
        createAnimation(moveButton, R.anim.move_animation);
        createAnimation(rotateButton, R.anim.rotate_animation);
        createAnimation(zoomButton, R.anim.zoom_animation);
        createAnimation(slideButton, R.anim.slide_animation);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {


        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                offsetX = x - image.getX();
                offsetY = y - image.getY();
                imageWidth = image.getWidth();
                imageHeight = image.getHeight();
                isDragging = true;
                return true;
            case  MotionEvent.ACTION_MOVE:
                if(isDragging){
                    float newX = x - offsetX;
                    float newY = y - offsetY;
                    image.setX(newX);
                    image.setY(newY);
                }
                return  true;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                return true;
            default:
                return  false;

        }


    }
}