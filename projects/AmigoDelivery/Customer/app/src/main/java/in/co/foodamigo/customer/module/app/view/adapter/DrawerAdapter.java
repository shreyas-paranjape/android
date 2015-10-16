package in.co.foodamigo.customer.module.app.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import java.util.List;

import in.co.foodamigo.customer.R;


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
        ImageView linkImage =(ImageView) convertView.findViewById(R.id.iv_drawer_link);
        linkImage.setBackgroundResource(getItem(position).getImageId());
    }
}
