package com.example.projecctforandroidlessons.presentation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.projecctforandroidlessons.R;

public class TelephonyManagerFragment extends Fragment {

    private TextView phoneStateTextView;
    private Button sendSmsButton;
    private TelephonyManager telephonyManager;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.MODIFY_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.MODIFY_PHONE_STATE,
                    Manifest.permission.SEND_SMS
            }, PERMISSION_REQUEST_CODE);
        } else {
            initializeTelephonyManager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeTelephonyManager();
            } else {
                Toast.makeText(getContext(), "Необходимые разрешения не предоставлены", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void initializeTelephonyManager() {
        int phoneState = telephonyManager.getCallState();
        switch (phoneState) {
            case TelephonyManager.CALL_STATE_IDLE:
                phoneStateTextView.setText("Телефон в режиме ожидания");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                phoneStateTextView.setText("Входящий звонок");
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                phoneStateTextView.setText("Телефон в разговоре");
                break;
        }

        sendSmsButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("1234567890", null, "Привет из приложения h!", null, null);
                Toast.makeText(getContext(), "SMS отправлено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Нет разрешения на отправку SMS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_telephony_manager, container, false);

        phoneStateTextView = view.findViewById(R.id.phone_state_text_view_id);
        sendSmsButton = view.findViewById(R.id.send_sms_button);

        return view;
    }
}
