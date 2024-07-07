package com.example.projecctforandroidlessons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class DestinationFragment extends Fragment {

    private RecyclerView recyclerView;
    private DestinationAdapter adapter;
    private List<Destination> destinatinList;

    public DestinationFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.second_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        destinatinList = new ArrayList<>();
        destinatinList.add(new Destination("Турция", "популярна"));
        destinatinList.add(new Destination("Египет", "популярна"));

        adapter = new DestinationAdapter(destinatinList);
        recyclerView.setAdapter(adapter);




    }
}