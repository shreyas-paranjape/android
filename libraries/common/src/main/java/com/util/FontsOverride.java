package com.util;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        if (fontAssetName != null) {
            final Typeface regular =
                    Typeface.createFromAsset(
                            context.getAssets(),
                            fontAssetName);
            replaceFont(staticTypefaceFieldName, regular);
        }
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
