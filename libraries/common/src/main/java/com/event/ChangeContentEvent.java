package com.event;

import android.os.Bundle;

import java.io.Serializable;

public class ChangeContentEvent implements Serializable {
    private final Class contentClass;
    private final Bundle data;
    private final boolean addToBackStack;

    public ChangeContentEvent(Class newContentClass) {
        this(newContentClass, new Bundle(), false);
    }

    public ChangeContentEvent(Class newContentClass, Bundle data) {
        this(newContentClass, data, false);
    }

    public ChangeContentEvent(Class newContentClass, Bundle data, boolean addToBackStack) {
        this.contentClass = newContentClass;
        this.data = data;
        this.addToBackStack = addToBackStack;
    }

    public Class getContentClass() {
        return contentClass;
    }

    public Bundle getData() {
        return data;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }
}
