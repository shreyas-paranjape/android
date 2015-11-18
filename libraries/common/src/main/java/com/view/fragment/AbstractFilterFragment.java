package com.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cache.ObjectCache;
import com.event.ChangeContentEvent;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public abstract class AbstractFilterFragment<T> extends Fragment {

    protected List<Filter<T>> filters = new ArrayList<>();
    protected Button btnFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Object cachedFilters = ObjectCache.get(getArgumentKey());
        if (cachedFilters != null) {
            filters = (List<Filter<T>>) cachedFilters;
        } else {
            filters = new ArrayList<>();
            ObjectCache.put(getArgumentKey(), filters);
        }
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        btnFilter = (Button) root.findViewById(getFilterButtonId());
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterClick();
            }
        });
        initView(root);
        return root;
    }

    protected abstract int getFilterButtonId();

    protected abstract void initView(View root);

    protected abstract int getLayoutId();

    protected void onFilterClick() {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        getListFragmentClass(),
                        new Bundle()));
    }

    protected abstract Class getListFragmentClass();

    protected abstract Object getArgumentKey();
}
