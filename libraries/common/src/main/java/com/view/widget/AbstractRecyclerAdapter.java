package com.view.widget;

import android.support.v7.widget.RecyclerView;

import com.util.IPredicate;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class
        AbstractRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private final List<T> itemList;

    protected AbstractRecyclerAdapter(List<T> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void sort(Comparator<T> comparator) {
        Collections.sort(itemList, comparator);
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

    public void filterSort(IPredicate<T> predicate, Comparator<T> comparator) {
        if (predicate != null) {
            filter(predicate);
        }
        if (comparator != null) {
            sort(comparator);
        }
        notifyDataSetChanged();
    }

}