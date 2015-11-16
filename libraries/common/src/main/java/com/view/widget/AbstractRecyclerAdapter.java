package com.view.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.event.ChangeContentEvent;
import com.util.IPredicate;
import com.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import de.greenrobot.event.EventBus;

public abstract class
        AbstractRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected LayoutInflater inflater;
    private final List<T> originalList;
    private final List<T> itemList;

    protected AbstractRecyclerAdapter(Context context, List<T> itemList) {
        this.originalList = new ArrayList<>();
        originalList.addAll(itemList);
        this.itemList = itemList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    public void filterSort(IPredicate<T> predicate, Comparator<T> comparator) {
        if (predicate != null) {
            itemList.clear();
            itemList.addAll(originalList);
            filter(predicate);
        }
        if (comparator != null) {
            Collections.sort(itemList, comparator);
        }
        notifyDataSetChanged();
    }

    private void filter(IPredicate<T> predicate) {
        Iterator<T> it = itemList.iterator();
        while (it.hasNext()) {
            T foo = it.next();
            if (!predicate.apply(foo)) {
                it.remove();
            }
        }
    }

    protected void onClick(Class newView, String dataKey, Serializable data) {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        newView,
                        Util.bundleSerializable(dataKey, data)));
    }


}