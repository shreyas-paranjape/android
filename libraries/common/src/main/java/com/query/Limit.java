package com.query;

import java.io.Serializable;

public class Limit implements Serializable {

    private final int startId;
    private final int count;

    public Limit(int startId, int count) {
        this.startId = startId;
        this.count = count;
    }


    public int getStartId() {
        return startId;
    }


    public int getCount() {
        return count;
    }

}
