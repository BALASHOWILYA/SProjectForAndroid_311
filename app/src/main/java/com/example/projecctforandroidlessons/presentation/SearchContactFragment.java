package com.example.projecctforandroidlessons.presentation;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecctforandroidlessons.R;


public class SearchContactFragment extends Fragment {


    private EditText contextNameEditText;
    private TextView searchResultTextView;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contextNameEditText = view.findViewById(R.id.contact_name_edit_text_id);
        searchResultTextView = view.findViewById(R.id.search_result_text_view_id);
        button = view.findViewById(R.id.search_contact_button);

        button.setOnClickListener(v->{
            String contactName = contextNameEditText.getText().toString();
            if(contactName.isEmpty()){
                Toast.makeText(getActivity(), "enter contact name", Toast.LENGTH_LONG).show();
            }

            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            String selection = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
            String[] selectionArgs = { contactName };

            Cursor cursor = getActivity().getContentResolver().query(uri, null,selection,selectionArgs, null );

            if(cursor != null && cursor.moveToFirst()){
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                cursor.close();

                searchResultTextView.setText("Имя " + name + " Телефон " + phoneNumber);
            }
            else{
                searchResultTextView.setText("No name");
                if(cursor != null){
                    cursor.close();
                }
            }








        });
    }
}