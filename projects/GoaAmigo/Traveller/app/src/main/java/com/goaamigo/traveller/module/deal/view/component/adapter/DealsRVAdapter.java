package com.goaamigo.traveller.module.deal.view.component.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.model.trip.Deals;
import com.goaamigo.traveller.R;

import java.util.List;

public class DealsRVAdapter extends RecyclerView.Adapter<DealsRVAdapter.dataViewHolder> {

    private List<Deals> dealData;
    private Context context;

    public DealsRVAdapter(List<Deals> dealData, Context context) {
        this.dealData = dealData;
        this.context = context;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deals, parent, false);
        dataViewHolder phv = new dataViewHolder(v);
        return phv;
    }

    @Override
    public void onBindViewHolder(dataViewHolder holder, int position) {
        holder.image.setImageResource(dealData.get(position).getImageDeal());
        holder.title.setText(dealData.get(position).getDealName());
        holder.description.setText(dealData.get(position).getDealDescription());
    }

    @Override
    public int getItemCount() {
        return dealData.size();
    }

    public static class dataViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView title;
        TextView description;
        public dataViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvDeals);
            image = (ImageView)itemView.findViewById(R.id.imgDealResult);
            title = (TextView)itemView.findViewById(R.id.tvDealName);
            description = (TextView)itemView.findViewById(R.id.tvDealDescription);
        }
    }
}
