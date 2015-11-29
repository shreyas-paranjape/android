package supplier.module.app.singleton;

import com.App;

public class SupplierApp extends App {

    @Override
    public String getBaseUri() {
        return "";
    }

    @Override
    public String getTag() {
        return SupplierApp.class.getName();
    }

    @Override
    public String getAccountType() {
        return "Amigo Delivery";
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
