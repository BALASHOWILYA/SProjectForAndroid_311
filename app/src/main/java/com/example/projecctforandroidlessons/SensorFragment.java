package com.example.projecctforandroidlessons;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SensorFragment extends Fragment {

    private Button startServiceButton, stopServiceButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startServiceButton = view.findViewById(R.id.startServiceButtonId);
        stopServiceButton = view.findViewById(R.id.stopServiceButtonId);

        startServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(getActivity(), SensorService.class);
            getActivity().startService(serviceIntent);
        });

        stopServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(getActivity(), SensorService.class);
            getActivity().stopService(serviceIntent);
        });



    }
}