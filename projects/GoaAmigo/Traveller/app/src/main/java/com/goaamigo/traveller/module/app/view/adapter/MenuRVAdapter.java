package com.goaamigo.traveller.module.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.model.MenuData;
import com.goaamigo.traveller.R;

import java.io.Serializable;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.dataViewHolder> {

    List<MenuData> data;
    private Context context;

    public MenuRVAdapter(Context context,List<MenuData> data) {
        this.context = context;
        this.data = data;
   }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_layout, viewGroup,false);
        dataViewHolder holder = new dataViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(dataViewHolder dataViewHolder, final int i) {
        dataViewHolder.title.setText(data.get(i).getTitle());
        dataViewHolder.description.setText(data.get(i).getDescription());
        dataViewHolder.image.setImageResource(data.get(i).getImageId());
        dataViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MenuButtonClicked(data.get(i)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class dataViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        ImageView image;
        TextView title;
        TextView description;
        public dataViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            image = (ImageView)itemView.findViewById(R.id.cardPhoto);
            title = (TextView)itemView.findViewById(R.id.cardTitle);
            description = (TextView)itemView.findViewById(R.id.cardDescription);
        }
    }

    public static class MenuButtonClicked implements Serializable {
        private final MenuData menuData;


        public MenuButtonClicked(MenuData menuData) {
            this.menuData = menuData;
        }

        public MenuData getMenuData() {
            return menuData;
        }
    }
}
