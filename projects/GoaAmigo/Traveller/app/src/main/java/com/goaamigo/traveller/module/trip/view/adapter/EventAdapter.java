package com.goaamigo.traveller.module.trip.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.model.trip.Day;
import com.goaamigo.model.trip.Event;
import com.goaamigo.traveller.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    protected final LayoutInflater inflater;
    protected final List<Event> data;
    protected final Context context;

    public EventAdapter(Context context,List<Event> list) {
        this.data = list;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.name.setText(data.get(i).getName());
        viewHolder.description.setText(data.get(i).getDescription());
        viewHolder.image.setImageResource(data.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv ;
        ImageView image;
        TextView name;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            image = (ImageView) itemView.findViewById(R.id.imgEvent);
            name = (TextView) itemView.findViewById(R.id.tvEventName);
            description = (TextView) itemView.findViewById(R.id.tvEventDescription);
        }
    }

}