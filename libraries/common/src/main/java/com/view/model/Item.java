package com.view.model;

import android.app.Fragment;

import java.io.Serializable;

public abstract class Item implements Serializable {

    private final String name;
    private final int imageId;

    public Item(){
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

}
