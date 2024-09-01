package com.example.projecctforandroidlessons.presentation;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.widget.Toast;

import com.example.projecctforandroidlessons.R;

public class CalendarFragment extends Fragment {

    private static final int REQUEST_CODE_CALENDAR = 100;
    private TextView calendarInfoTextView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarInfoTextView = view.findViewById(R.id.calendarInfoTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button openCalendarButton = view.findViewById(R.id.openCalendarButton);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_CALENDAR},
                    REQUEST_CODE_CALENDAR);
        } else {
            openCalendar();
        }
        openCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });

        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_CALENDAR) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCalendar();
            } else {

                Toast.makeText(requireContext(), "Необходимо разрешение на чтение календаря", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCalendar() {
        // Получение списка календарей
        ContentResolver contentResolver = requireActivity().getContentResolver();
        Uri uri = Calendars.CONTENT_URI;
        String[] projection = new String[]{
                Calendars._ID,
                Calendars.ACCOUNT_NAME,
                Calendars.CALENDAR_DISPLAY_NAME
        };

        Cursor cursor = contentResolver.query(uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder calendarInfo = new StringBuilder();
            do {
                long calID = cursor.getLong(0);  // Извлечение ID календаря
                String accountName = cursor.getString(1);  // Имя учетной записи
                String displayName = cursor.getString(2);  // Имя календаря

                calendarInfo.append("Calendar ID: ").append(calID).append("\n");
                calendarInfo.append("Account Name: ").append(accountName).append("\n");
                calendarInfo.append("Information: ").append(displayName).append("\n\n");

                Uri calendarUri = ContentUris.withAppendedId(CalendarContract.CONTENT_URI.buildUpon().appendPath("time").build(), calID);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(calendarUri);

                if (cursor.isFirst()) {
                    startActivity(intent);
                }

            } while (cursor.moveToNext());
            cursor.close();

            calendarInfoTextView.setText(calendarInfo.toString());
        }
    }
}