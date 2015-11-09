package com.goaamigo.traveller.module.app.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goaamigo.traveller.R;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import java.util.List;


public class DrawerAdapter extends NavigationDrawerListAdapter {

    public DrawerAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    protected int getItemId() {
        return R.layout.item_drawer_link;
    }

    @Override
    protected void initView(View convertView, int position) {
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(getItem(position).getName());
        ImageView linkImage = (ImageView) convertView.findViewById(R.id.iv_drawer_link);
        linkImage.setBackgroundResource(getItem(position).getImageId());


        //notification icon on the drawer
        LinearLayout notification=(LinearLayout) convertView.findViewById(R.id.notification1);

         //logout icon on the drawer
        LinearLayout logout=(LinearLayout) convertView.findViewById(R.id.logout);

         //settings icon on the drawer
        LinearLayout settings=(LinearLayout) convertView.findViewById(R.id.settings1);


        notification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View convertview) {

                Toast.makeText(getContext(),"Notification", Toast.LENGTH_SHORT).show();
                Log.i("Notification", "Notication");
            }
        });

    }
}
