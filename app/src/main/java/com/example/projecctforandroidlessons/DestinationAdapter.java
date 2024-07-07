package com.example.projecctforandroidlessons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private List<Destination> destinations;

    public DestinationAdapter(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).
                inflate(R.layout.destination_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.destinationNameTextView.setText(destination.getDestination_name());
        holder.popularityStatusTextView.setText(destination.getPopularity_status());




    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView destinationNameTextView;
        TextView popularityStatusTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationNameTextView = itemView.findViewById(R.id.destination_name);
            popularityStatusTextView = itemView.findViewById(R.id.popularity_status);

        }
    }
}
