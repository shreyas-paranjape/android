package com.util;

import android.os.Bundle;

import java.io.Serializable;

public class Util {

    public static Bundle bundleSerializable(String key, Serializable value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        return bundle;
    }

    private Util() {
        throw new IllegalStateException("");
    }
}
