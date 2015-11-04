package com.goaamigo.traveller.module.trip.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.goaamigo.model.trip.Day;
import com.goaamigo.model.trip.Event;
import com.goaamigo.traveller.databinding.ItemEventBinding;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    protected final LayoutInflater inflater;
    protected final List<Event> data;

    public EventAdapter(Context context, Day day) {
        this.data = new ArrayList<>();
        data.add(new Event("event 1"));
        data.add(new Event("event 2"));
        data.add(new Event("EVENT 3"));
        data.add(new Event("EVENT 4"));
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemEventBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Event event = data.get(i);
        viewHolder.cardView.setEvent(event);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemEventBinding cardView;

        public ViewHolder(ItemEventBinding cardView) {
            super(cardView.getRoot());
            this.cardView = cardView;
        }
    }

}