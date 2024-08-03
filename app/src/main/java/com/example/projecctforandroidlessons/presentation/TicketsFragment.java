package com.example.projecctforandroidlessons.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.connection.ConSQL;
import com.example.projecctforandroidlessons.presentation.ui.FlightInfo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class TicketsFragment extends Fragment {

    Connection connection;

    private RecyclerView recyclerView;
    private FlightInfoAdapter adapter;
    private List<FlightInfo> flightInfoList;

    private void init(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tickets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        flightInfoList = new ArrayList<>();
        //flightInfoList.add(new FlightInfo("6 990 ₽", "03:15", "07:10", "4ч в пути / Без пересадок", "VKO", "AER"));
        //flightInfoList.add(new FlightInfo("7 500 ₽", "04:30", "08:20", "5ч в пути / Одна пересадка", "DME", "SVO"));
        //flightInfoList.add(new FlightInfo("8 200 ₽", "05:45", "10:00", "4ч 15м в пути / Без пересадок", "LED", "AER"));
        //flightInfoList.add(new FlightInfo("5 800 ₽", "08:00", "12:30", "4ч 30м в пути / Две пересадки", "SVO", "PEE"));
        //flightInfoList.add(new FlightInfo("9 100 ₽", "10:15", "15:05", "4ч 50м в пути / Одна пересадка", "VKO", "KUF"));

        ConSQL c = new ConSQL();
        flightInfoList = c.getFlightInfoList();


        adapter = new FlightInfoAdapter(flightInfoList);
        recyclerView.setAdapter(adapter);

    }
}