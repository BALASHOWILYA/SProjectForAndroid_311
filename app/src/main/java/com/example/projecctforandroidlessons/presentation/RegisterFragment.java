package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projecctforandroidlessons.R;

public class RegisterFragment extends Fragment {

    private EditText password, email, name;
    private Button button;
    private TextView status;
    private RegisterViewModel registerViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        init(view);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getStatus().observe(getViewLifecycleOwner(), status::setText);

        registerViewModel.getIsSuccess().observe(getViewLifecycleOwner(), isSuccess -> {
            if (isSuccess) {
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });

        button.setOnClickListener(v -> {
            String userName = name.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            status.setText("Sending data...");
            registerViewModel.registerUser(userName, userEmail, userPassword);
        });

        return view;
    }

    private void init(View view) {
        email = view.findViewById(R.id.email_edit_text_id);
        password = view.findViewById(R.id.password_edit_text_id);
        name = view.findViewById(R.id.user_edit_text_id);
        status = view.findViewById(R.id.status_text_id);
        button = view.findViewById(R.id.button_id);
    }
}