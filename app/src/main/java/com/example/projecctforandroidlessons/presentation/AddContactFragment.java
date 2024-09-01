package com.example.projecctforandroidlessons.presentation;

import android.content.Intent;
import android.provider.ContactsContract;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.projecctforandroidlessons.R;

public class AddContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        Button addContactButton = view.findViewById(R.id.add_contact_button);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);


                intent.putExtra(ContactsContract.Intents.Insert.NAME, "Имя Фамилия")
                        .putExtra(ContactsContract.Intents.Insert.PHONE, "1234567890")
                        .putExtra(ContactsContract.Intents.Insert.EMAIL, "example@mail.com")
                        .putExtra(ContactsContract.Intents.Insert.COMPANY, "Название компании")
                        .putExtra(ContactsContract.Intents.Insert.JOB_TITLE, "Должность");
                startActivity(intent);
            }
        });

        return view;
    }
}