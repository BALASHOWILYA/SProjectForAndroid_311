package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.projecctforandroidlessons.R;


public class CreateAccountFragment extends Fragment {

    private Button enterButton;

    private void init(View view){
        enterButton = view.findViewById(R.id.enter_account_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        enterButton.setOnClickListener(v->{
            Toast.makeText(getActivity(),"Entered account", Toast.LENGTH_LONG).show();
        });
    }
}