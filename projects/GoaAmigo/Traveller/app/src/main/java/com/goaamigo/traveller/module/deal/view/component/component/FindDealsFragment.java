package com.goaamigo.traveller.module.deal.view.component.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.model.trip.Deals;
import com.goaamigo.model.trip.Trip;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.deal.view.component.adapter.DealsRVAdapter;
import com.goaamigo.traveller.module.trip.view.adapter.TripRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class FindDealsFragment extends Fragment {
    List<Deals> list;
    DealsRVAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Find deals");
        View v = inflater.inflate(R.layout.fragment_find_deal, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        list = new ArrayList<>();
        list.add(new Deals("deal 1","this is deal 1",R.drawable.image4));
        list.add(new Deals("deal 2","this is deal 2",R.drawable.image4));
        list.add(new Deals("deal 3","this is deal 3",R.drawable.image4));


        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        adapter = new DealsRVAdapter(list,getActivity());
        rv.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
