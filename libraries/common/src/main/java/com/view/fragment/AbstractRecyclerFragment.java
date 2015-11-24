package com.view.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.event.ChangeContentEvent;
import com.view.widget.AbstractRecyclerAdapter;

import de.greenrobot.event.EventBus;

public abstract class AbstractRecyclerFragment<T> extends Fragment {

    protected final static String dialogFragmentTag = "DIALOG";

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

    protected void onFilterClick() {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        getFilterFragmentClass(), new Bundle()));
    }

    protected void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(dialogFragmentTag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = getFilterFragment();
        newFragment.show(ft, dialogFragmentTag);
    }

    protected AbstractFilterFragment<T> getFilterFragment() {
        return null;
    }

    protected abstract Class getFilterFragmentClass();

    protected abstract int getLayoutId();

    protected abstract int getMenuId();

    protected abstract int getRecyclerId();

    protected abstract void initView(View root);

    protected abstract AbstractRecyclerAdapter getAdapter();
}
