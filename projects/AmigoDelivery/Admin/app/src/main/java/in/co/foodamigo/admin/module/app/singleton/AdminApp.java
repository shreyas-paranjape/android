package in.co.foodamigo.admin.module.app.singleton;

import android.app.Application;

import com.cache.ObjectCache;
import com.orm.SugarContext;
import com.util.Constant;
import com.util.FontsOverride;
import com.util.IPredicate;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.DummyData;
import model.catalogue.Product;


public class AdminApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        DummyData.createDummyData("goaamigo.admin");
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
    public void onTerminate() {
        super.onTerminate();
    }

}
