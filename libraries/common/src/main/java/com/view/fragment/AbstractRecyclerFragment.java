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

import com.cache.ObjectCache;
import com.event.ChangeContentEvent;
import com.view.model.Filter;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.greenrobot.event.EventBus;

public abstract class AbstractRecyclerFragment<T> extends Fragment {

    protected List<Filter<T>> filters;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object cachedFilters = ObjectCache.get(getArgumentKey());
        if (cachedFilters != null) {
            filters = (List<Filter<T>>) cachedFilters;
        } else {
            filters = new ArrayList<>();
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuId(), menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        initRecycler(v);
        initView(v);
        return v;
    }

    private void initRecycler(View rootView) {
        RecyclerView productsRecyclerView =
                (RecyclerView) rootView.findViewById(getRecyclerId());
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsRecyclerView.setAdapter(getAdapter());
    }


    protected List<T> applyFilters(List<T> objects, List<Filter<T>> filters) {
        Iterator<T> iterator = objects.iterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            for (Filter filter : filters) {
                if (filter.getPredicate().apply(object)) {
                    iterator.remove();
                }
            }
        }
        return objects;
    }

    protected void onFilterClick() {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        getFilterFragmentClass(), new Bundle()));
    }

    protected abstract Class getFilterFragmentClass();

    protected abstract int getLayoutId();

    protected abstract int getMenuId();

    protected abstract int getRecyclerId();

    protected abstract void initView(View root);

    protected abstract AbstractRecyclerAdapter getAdapter();

    protected abstract String getArgumentKey();

}
