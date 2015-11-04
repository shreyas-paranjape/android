package com.goaamigo.model.trip;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Day implements Serializable {

    private long id;
    private int mThumbnail;

    @Column(name = "name")
    private String name;


    public Day() {
    }

    public Day(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(int mThumbnail) {
        this.mThumbnail = mThumbnail;
    }
}
