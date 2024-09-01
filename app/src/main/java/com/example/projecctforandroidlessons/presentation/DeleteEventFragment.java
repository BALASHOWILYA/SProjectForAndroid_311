package com.example.projecctforandroidlessons.presentation;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecctforandroidlessons.R;


public class DeleteEventFragment extends Fragment {

    private static final int REQUEST_CODE_CALENDAR = 100;
    private EditText eventIdEditText;
    private Button deleteButton;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delete_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventIdEditText = view.findViewById(R.id.event_id_et);
        deleteButton = view.findViewById(R.id.delete_button);
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.WRITE_CALENDAR},
                    REQUEST_CODE_CALENDAR);
        } else {
            deleteButton.setOnClickListener(v->{
                onDeleteClick(v);
            });
        }

    }



    private void onDeleteClick(View v) {
        String eventIdStr =eventIdEditText.getText().toString();
        if(eventIdStr.isEmpty()){
            Toast.makeText(getActivity(), "Please enter a valid event ID", Toast.LENGTH_LONG).show();
        }
        long eventId = Long.parseLong(eventIdStr);

        Uri deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);

        ContentResolver cr = requireActivity().getContentResolver();
        int rows = cr.delete(deleteUri, null, null);

        if(rows > 0){
            Toast.makeText(getActivity(), "Event was removed", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getActivity(), "Failed to remoce event", Toast.LENGTH_LONG).show();
        }

    }
}