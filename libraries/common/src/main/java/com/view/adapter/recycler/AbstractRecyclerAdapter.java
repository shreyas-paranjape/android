package com.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.cache.ObjectCache;
import com.event.ChangeContentEvent;
import com.util.IPredicate;
import com.util.Util;
import com.view.model.Filter;

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

    @SuppressWarnings("unchecked")
    private final List<Filter<T>> cachedFilters =
            (List<Filter<T>>) ObjectCache.get(getCacheFilterKey());
    protected LayoutInflater inflater;
    private final List<T> originalList;
    private final List<T> itemList;

    protected AbstractRecyclerAdapter(Context context, List<T> itemList) {
        this.originalList = new ArrayList<>(itemList);
        this.itemList = itemList;
        applyFilters();
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

    public void sort(Comparator<T> comparator) {
        Collections.sort(itemList, comparator);
        notifyDataSetChanged();
    }

    protected void resetFilters() {
        itemList.clear();
        itemList.addAll(originalList);
        notifyDataSetChanged();
    }

    protected void filter(IPredicate<T> predicate) {
        itemList.clear();
        itemList.addAll(originalList);
        Iterator<T> it = itemList.iterator();
        while (it.hasNext()) {
            T foo = it.next();
            if (!predicate.apply(foo)) {
                it.remove();
            }
        }
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public void onEvent(FilterEvent event) {
        if (cachedFilters.size() == 0) {
            resetFilters();
        } else {
            applyFilters();
        }
    }

    private void applyFilters() {
        itemList.clear();
        itemList.addAll(originalList);
        Iterator<T> it = itemList.iterator();
        while (it.hasNext()) {
            T foo = it.next();
            for (Filter<T> filter : cachedFilters) {
                if (!filter.getPredicate().apply(foo)) {
                    it.remove();
                }
            }
        }
    }

    protected void onClick(Class newView, String dataKey, Serializable data) {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        newView,
                        Util.bundleSerializable(dataKey, data)));
    }

    protected abstract String getCacheFilterKey();

    public static class FilterEvent {
    }

    public static class Sort<T> {
        private final Comparator<T> comparator;

        public Sort(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        public Comparator<T> getComparator() {
            return comparator;
        }

    }


}