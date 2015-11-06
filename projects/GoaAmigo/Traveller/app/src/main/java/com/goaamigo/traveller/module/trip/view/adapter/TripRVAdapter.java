package com.goaamigo.traveller.module.trip.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.model.trip.trip.Trip;
import com.goaamigo.traveller.R;

import java.util.List;

public class TripRVAdapter extends RecyclerView.Adapter<TripRVAdapter.dataViewHolder> {

    List<Trip> tripData;
    private Context context;

    public TripRVAdapter(List<Trip> tripData, Context context) {
        this.tripData = tripData;
        this.context = context;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_result, parent, false);
        dataViewHolder phv = new dataViewHolder(v);
        return phv;
    }

    @Override
    public void onBindViewHolder(dataViewHolder holder, int position) {
        holder.image.setImageResource(tripData.get(position).getImageId());
        holder.title.setText(tripData.get(position).getTripName());
        holder.description.setText(tripData.get(position).getTripDescription());
    }

    @Override
    public int getItemCount() {
        return tripData.size();
    }

    public static class dataViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView title;
        TextView description;
        public dataViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvTrip);
            image = (ImageView)itemView.findViewById(R.id.imgTripResult);
            title = (TextView)itemView.findViewById(R.id.tvTripName);
            description = (TextView)itemView.findViewById(R.id.tvTripDescription);
        }
    }
}
