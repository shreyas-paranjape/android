package com.cache;

import android.util.LruCache;

/**
 * Initialize in application class to make it a singleton
 */
public class ObjectCache {
    private static final LruCache<Object, Object> cache = new LruCache<>(100);

    public static Object get(Object key) {
        return cache.get(key);
    }

    public static void put(Object key, Object value) {
        cache.put(key, value);
    }

    public static int size() {
        return cache.size();
    }

}
