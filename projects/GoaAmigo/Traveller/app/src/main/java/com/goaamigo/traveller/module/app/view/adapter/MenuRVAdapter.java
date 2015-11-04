package com.goaamigo.traveller.module.app.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.model.MenuData;

import java.util.List;

import de.greenrobot.event.EventBus;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.dataViewHolder> {

    List<MenuData> menuDataList;
    private Context context;

    public MenuRVAdapter(Context context, List<MenuData> data) {
        this.context = context;
        this.menuDataList = data;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_layout, viewGroup, false);
        dataViewHolder holder = new dataViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(dataViewHolder dataViewHolder, final int i) {
        dataViewHolder.title.setText(menuDataList.get(i).getTitle());
        dataViewHolder.description.setText(menuDataList.get(i).getDescription());
        dataViewHolder.image.setImageResource(menuDataList.get(i).getImageId());
        dataViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dataBundle = new Bundle();
                dataBundle.putSerializable("MENUDATA", menuDataList.get(i));
                EventBus.getDefault().post(new ChangeContentEvent(ChangeContentEvent.ContentType.ACTIVITY, dataBundle) {
                    @Override
                    public Class getContentClass() {
                        return menuDataList.get(i).getClazz();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuDataList.size();
    }

    public static class dataViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView title;
        TextView description;

        public dataViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            image = (ImageView) itemView.findViewById(R.id.cardPhoto);
            title = (TextView) itemView.findViewById(R.id.cardTitle);
            description = (TextView) itemView.findViewById(R.id.cardDescription);
        }
    }

   /* public static class MenuButtonClicked implements Serializable {
        private final MenuData menuData;


        public MenuButtonClicked(MenuData menuData) {
            this.menuData = menuData;
        }

        public MenuData getMenuData() {
            return menuData;
        }
    }*/
}
