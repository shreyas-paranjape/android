package com.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.view.widget.AbstractRecyclerAdapter;

public abstract class AbstractRecyclerFragment
        extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuId(), menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        initRecycler(v);
        initView(v);
        return v;
    }

    private void initRecycler(View rootView) {
        RecyclerView productsRecyclerView = (RecyclerView) rootView.findViewById(getRecyclerId());
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsRecyclerView.setAdapter(getAdapter());
    }

    protected abstract int getLayoutId();

    protected abstract int getMenuId();

    protected abstract int getRecyclerId();

    protected abstract void initView(View root);

    protected abstract AbstractRecyclerAdapter getAdapter();

}
