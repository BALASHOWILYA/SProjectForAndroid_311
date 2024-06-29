package com.example.projecctforandroidlessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class CreateAccountFragment extends Fragment {

    private Button enterButton;

    private void init(View view){
        enterButton = view.findViewById(R.id.enter_account_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        init(view);


        enterButton.setOnClickListener(v->{
            Toast.makeText(getActivity(),"Entered account", Toast.LENGTH_LONG).show();
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }
}