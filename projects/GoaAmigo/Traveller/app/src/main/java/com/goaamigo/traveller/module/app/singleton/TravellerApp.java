package com.goaamigo.traveller.module.app.singleton;

import com.App;
import com.order.CurrentOrderManager;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.DummyData;
import model.catalogue.Product;

public class TravellerApp extends App {

    private static final String baseUri = "http://192.168.10.172:3000";
    private static final String accountType = "Goa amigo";

    @Override
    public void onCreate() {
        super.onCreate();
        DummyData.createDummyData("traveller");
    }

    protected List<Filter<Product>> filters = new ArrayList<>();

    @Override
    public String getBaseUri() {
        return baseUri;
    }

    @Override
    public String getTag() {
        return TravellerApp.class.getName();
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public List<Filter<Product>> getFilters() {
        return filters;
    }

    @Override
    protected String getThinFontAssetName() {
        return "Raleway-Thin.ttf";
    }

    @Override
    protected String getRegularFontAssetName() {
        return "Raleway-Regular.ttf";
    }
}
