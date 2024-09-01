package com.example.projecctforandroidlessons.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.projecctforandroidlessons.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactsFragment extends Fragment {

    private ListView contactsListView;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactsListView = view.findViewById(R.id.contacts_list_view);
        requestContacts();
        return view;
    }

    private void requestContacts() {
        if (getActivity() == null) return;

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(requireContext()), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            getContacts();
        }
    }

    @SuppressLint("Range")
    private void getContacts() {
        List<String> contacts = new ArrayList<>();
        ContentResolver contentResolver = requireContext().getContentResolver(); // Use requireContext() here
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (true) {
                        assert phoneCursor != null;
                        if (!phoneCursor.moveToNext()) break;
                        String phoneNumber = phoneCursor.getString(
                                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.d("contacts", name);
                        Log.d("contacts", phoneNumber);
                        contacts.add(name + " : " + phoneNumber);
                    }
                    phoneCursor.close();
                }
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, contacts);
        contactsListView.setAdapter(adapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            } else {
                // Обработка случая, когда разрешение не предоставлено
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    // Показать объяснение необходимости разрешения
                    new AlertDialog.Builder(Objects.requireNonNull(requireContext()))
                            .setTitle("Необходим доступ к контактам")
                            .setMessage("Для отображения контактов необходимо разрешение на их чтение. Пожалуйста, предоставьте доступ.")
                            .setPositiveButton("Повторить", (dialog, which) -> requestContacts())
                            .setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                } else {
                    // Разрешение было отклонено и не должно запрашиваться снова, можно показать сообщение
                    new AlertDialog.Builder(Objects.requireNonNull(requireContext()))
                            .setTitle("Доступ запрещен")
                            .setMessage("Вы отказали в доступе к контактам. Чтобы предоставить доступ, перейдите в настройки приложения.")
                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                }
            }
        }
    }

}
