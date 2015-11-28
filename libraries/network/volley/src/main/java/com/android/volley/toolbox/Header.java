package com.android.volley.toolbox;

public class Header {
    private String name;
    private String value;

    public Header(String key, String s) {
        this.name = key;
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
