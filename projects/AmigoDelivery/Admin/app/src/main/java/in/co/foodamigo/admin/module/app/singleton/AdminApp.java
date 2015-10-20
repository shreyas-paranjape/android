package in.co.foodamigo.admin.module.app.singleton;

import android.app.Application;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.util.FontsOverride;

import in.co.foodamigo.admin.module.catalogue.model.Product;
import in.co.foodamigo.admin.module.catalogue.model.ProductCategory;
import in.co.foodamigo.admin.module.catalogue.model.Supplier;


public class AdminApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        createDummyData();
    }

    private void createDummyData() {
        ProductCategory root = new ProductCategory(1, "root");
        SugarRecord.save(root);
        ProductCategory goan = new ProductCategory(2, "goan",root);
        SugarRecord.save(goan);
        ProductCategory snacks = new ProductCategory(3, "snacks",root);
        SugarRecord.save(snacks);
        Supplier awesomeRest = new Supplier(1, "restaurant X", "");
        SugarRecord.save(awesomeRest);
        SugarRecord.save(new Product(1,
                "Prawn curry",
                "Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices",
                80,
                "",
                goan,
                awesomeRest));
        SugarRecord.save(new Product(2,
                "butter chicken sandwich",
                "Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices",
                80,
                "",
                snacks,
                awesomeRest));

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
