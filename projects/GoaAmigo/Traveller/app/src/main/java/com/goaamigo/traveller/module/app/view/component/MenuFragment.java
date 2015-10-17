package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goaamigo.model.MenuData;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.MenuRVAdapter;
import com.goaamigo.traveller.module.deal.view.component.FindDealsFragment;
import com.goaamigo.traveller.module.trip.view.component.SearchTripFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

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
        MenuData data = new MenuData("Home", R.drawable.ic_home_black_24dp, "Home description");
        list.add(data);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        adapter = new MenuRVAdapter(getActivity(),list);
        rv.setAdapter(adapter);
    }
}
