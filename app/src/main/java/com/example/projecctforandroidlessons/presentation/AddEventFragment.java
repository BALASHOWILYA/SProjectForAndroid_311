package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecctforandroidlessons.R;

import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.GregorianCalendar;

public class AddEventFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");

        calIntent.putExtra(Events.TITLE, "Заголовок");
        calIntent.putExtra(Events.EVENT_LOCATION, "Место проведения");
        calIntent.putExtra(Events.DESCRIPTION, "Описание");

        GregorianCalendar calDate = new GregorianCalendar(2025, 3, 23);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis());
        calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=10;WKST=SU;BYDAY=TU,TH");

        startActivity(calIntent);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }
}