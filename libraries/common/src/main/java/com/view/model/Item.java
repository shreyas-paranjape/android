package com.view.model;

import android.app.Fragment;

import java.io.Serializable;

public abstract class Item implements Serializable {

    private final String name;
    private final int imageId;

    public Item() {
        this.name = "";
        this.imageId = 0;
    }

    public Item(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public abstract Fragment getDisplayFragment();

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
