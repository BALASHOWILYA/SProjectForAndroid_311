package com.example.projecctforandroidlessons.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projecctforandroidlessons.R;


public class SetAlarmFragment extends Fragment {

    private EditText titleEditText;
    private EditText hourEditText;
    private EditText minuteEditText;
    private Button setAlarmButton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_alarm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleEditText = view.findViewById(R.id.title_et);
        hourEditText = view.findViewById(R.id.hour_et);
        minuteEditText = view.findViewById(R.id.minutes_et);
        setAlarmButton = view.findViewById(R.id.set_alarm_button);

        setAlarmButton.setOnClickListener(v->{
            onSetAlarmClick(v);
        });
    }

    private void onSetAlarmClick(View v) {
        String  title = titleEditText.getText().toString();
        int  hour = Integer.parseInt(hourEditText.getText().toString());
        int  munites = Integer.parseInt(minuteEditText.getText().toString());

        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, title);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour);
        i.putExtra(AlarmClock.EXTRA_MINUTES, munites);

        if (i.resolveActivity(requireActivity().getPackageManager()) != null){
            startActivity(i);
        }
    }
}