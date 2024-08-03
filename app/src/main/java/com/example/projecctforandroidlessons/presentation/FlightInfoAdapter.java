package com.example.projecctforandroidlessons.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecctforandroidlessons.R;
import com.example.projecctforandroidlessons.presentation.ui.FlightInfo;

import java.util.List;

public class FlightInfoAdapter extends RecyclerView.Adapter<FlightInfoAdapter.ViewHolder> {

    private List<FlightInfo> flightInfoList;

    public FlightInfoAdapter(List<FlightInfo> flightInfoList) {
        this.flightInfoList = flightInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlightInfo flightInfo = flightInfoList.get(position);
        holder.price.setText(flightInfo.getPrice());
        holder.timeStart.setText(flightInfo.getTimeStart());
        holder.timeEnd.setText(flightInfo.getTimeEnd());
        holder.duration.setText(flightInfo.getDuration());
        holder.from.setText(flightInfo.getFrom());
        holder.to.setText(flightInfo.getTo());
    }

    @Override
    public int getItemCount() {
        return flightInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView price, timeStart, timeEnd, duration, from, to;

        public ViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            timeStart = itemView.findViewById(R.id.time_start);
            timeEnd = itemView.findViewById(R.id.time_end);
            duration = itemView.findViewById(R.id.duration);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
        }
    }
}