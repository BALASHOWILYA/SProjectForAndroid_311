package com.example.projecctforandroidlessons.presentation;

// Импортируем необходимые классы
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecctforandroidlessons.R;

public class EditContactFragment extends Fragment {

    private EditText contactNameEditText;
    private Button editContactButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        contactNameEditText = view.findViewById(R.id.contact_name_edit_text);

        editContactButton = view.findViewById(R.id.edit_contact_button);

        editContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем имя контакта из EditText
                String contactName = contactNameEditText.getText().toString();
                if (contactName.isEmpty()) {
                    Toast.makeText(getActivity(), "Введите имя контакта", Toast.LENGTH_SHORT).show();
                    return;
                }

                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] projection = new String[] { ContactsContract.Contacts._ID };
                String selection = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
                String[] selectionArgs = { contactName };

                Cursor cursor = getActivity().getContentResolver().query(uri, projection, selection, selectionArgs, null);

                if (cursor != null && cursor.moveToFirst()) {
                    String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    cursor.close();

                    Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, contactId);

                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setDataAndType(contactUri, ContactsContract.Contacts.CONTENT_ITEM_TYPE);

                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Контакт не найден", Toast.LENGTH_SHORT).show();
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        });

        return view;
    }
}
