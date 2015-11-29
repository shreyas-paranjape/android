package admin.module.app.singleton;

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
        return "";
    }

    @Override
    public String getTag() {
        return AdminApp.class.getName();
    }

    @Override
    public String getAccountType() {
        return "Admin";
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
