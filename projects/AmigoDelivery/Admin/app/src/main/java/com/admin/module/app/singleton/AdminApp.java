package com.admin.module.app.singleton;

import com.App;
import com.cache.ObjectCache;
import com.util.Constant;
import com.util.IPredicate;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.DummyData;
import model.catalogue.Product;


public class AdminApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        DummyData.createDummyData("admin");
        List<Filter<Product>> filterList = new ArrayList<>();
        filterList.add(new Filter<>("xyz", new IPredicate<Product>() {
            @Override
            public boolean apply(Product type) {
                return type.getName().contains("1");
            }
        }, null));
        ObjectCache.put(Constant.PRODUCT, filterList);
    }


    @Override
    public String getBaseUri() {
        return "http://192.168.10.172:3000";
    }

    @Override
    public String getTag() {
        return AdminApp.class.getName();
    }

    @Override
    public String getAccountType() {
        return "The admin";
    }

    @Override
    protected String getThinFontAssetName() {
        return "Raleway-Thin.ttf";
    }

    @Override
    protected String getRegularFontAssetName() {
        return "Raleway-Regular.ttf";
    }

    @Override
    public String getSharedPrefKey() {
        return "com.admin.shared_pref";
    }
}
