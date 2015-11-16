package com.event;

import android.os.Bundle;

import java.io.Serializable;

public class ChangeContentEvent implements Serializable {
    private ContentType newContent;
    private final Class contentClass;
    private final Bundle data;

    public ChangeContentEvent(ContentType newContent, Bundle data) {
        this.newContent = newContent;
        this.contentClass = null;
        this.data = data;
    }

    public ChangeContentEvent(Class newContentClass, Bundle data) {
        this.contentClass = newContentClass;
        this.data = data;
    }

    public ContentType getNewContent() {
        return newContent;
    }

    public Class getContentClass() {
        return contentClass;
    }

    public Bundle getData() {
        return data;
    }

    public enum ContentType {
        ACTIVITY, FRAGMENT
    }
}
