package com.view.model;

import android.view.View;

import com.util.IPredicate;

import java.io.Serializable;

public class Filter<T> implements Serializable {

    private final String code;
    private final IPredicate<T> predicate;
    private final View view;


    public Filter(String code, IPredicate<T> predicate, View view) {
        this.code = code;
        this.predicate = predicate;
        this.view = view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter<?> filter = (Filter<?>) o;

        return code.equals(filter.code);

    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    public String getCode() {
        return code;
    }

    public IPredicate<T> getPredicate() {
        return predicate;
    }

    public View getView() {
        return view;
    }
}
