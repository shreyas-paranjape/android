package supplier.module.app.singleton;

import com.App;

public class SupplierApp extends App {

    @Override
    public String getBaseUri() {
        return "http://192.168.10.172:3000";
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

    @Override
    public String[] getAppCredentials() {
        return new String[]{
                "RDV7ZQY7XPKBXX2UC7WXNYE5EGYGWZPT",
                "3GJY6MZ3XEF2GUX525ULE7PHG3KZQZ2V"
        };
    }

    public String getSecretKey() {
        return "3v3ryth1ng1s4w3s0m3";
    }
}
