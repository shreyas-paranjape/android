package in.co.foodamigo.customer.module.app.singleton;

import com.App;

import model.DummyData;


public class CustomerApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        DummyData.createDummyData("food.customer");
    }

    @Override
    public String getBaseUri() {
        return "";
    }

    @Override
    public String getTag() {
        return CustomerApp.class.getName();
    }

    @Override
    public String getAccountType() {
        return "Food Amigo";
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