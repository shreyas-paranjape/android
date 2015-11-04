package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.MenuRVAdapter;
import com.goaamigo.traveller.module.app.view.model.MenuData;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private List<MenuData> list;
    MenuRVAdapter adapter;

    private static final String TAG = MenuFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        list = new ArrayList<>();
        list.add(new MenuData("Search Trip", R.drawable.ic_home_black_24dp, "Home description"));
        list.add(new MenuData("login to trip", R.drawable.ic_account_circle_black_24dp, "see your trip"));
        list.add(new MenuData("Start map activity",R.drawable.ic_add_shopping_cart_black_24dp, "Description",MapsActivity.class));

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        adapter = new MenuRVAdapter(getActivity(),list);
        rv.setAdapter(adapter);

    }
}
