package com.goaamigo.traveller.module.app.singleton;

import com.App;

public class TravellerApp extends App {

    private static final String baseUri = "http://192.168.10.172:3000";
    private static final String accountType = "Goa amigo";

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

    @Override
    protected String getThinFontAssetName() {
        return "Raleway-Thin.ttf";
    }

    @Override
    protected String getRegularFontAssetName() {
        return "Raleway-Regular.ttf";
    }
}
