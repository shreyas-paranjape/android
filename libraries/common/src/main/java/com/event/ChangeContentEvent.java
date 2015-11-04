package com.event;

import android.os.Bundle;

import java.io.Serializable;

public abstract class ChangeContentEvent implements Serializable {
    private final ContentType newContent;

    public Bundle getData() {
        return data;
    }

    private final Bundle data;

    public ChangeContentEvent(ContentType newContent, Bundle data) {
        this.newContent = newContent;
        this.data = data;
    }

    public ContentType getNewContent() {
        return newContent;
    }

    public abstract Class getContentClass();

    public enum ContentType {
        ACTIVITY, FRAGMENT
    }
}
