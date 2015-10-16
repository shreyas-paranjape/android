package in.co.foodamigo.customer.module.app.singleton;

import android.app.Application;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.util.FontsOverride;

import delivery.model.catalogue.Product;
import delivery.model.catalogue.ProductCategory;
import delivery.model.catalogue.ProductParty;
import delivery.model.profile.Party;
import in.co.foodamigo.customer.module.order.controller.OrderManager;

public class CustomerApp extends Application {

    private final OrderManager orderManager = new OrderManager();

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        createDummyData();
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    private void createDummyData() {
        ProductCategory goan = new ProductCategory(1, "goan");
        SugarRecord.save(goan);
        ProductCategory snacks = new ProductCategory(2, "snacks");
        SugarRecord.save(snacks);
        Party awesomeRest = new Party(1, "restaurant X", "");
        SugarRecord.save(awesomeRest);
        Product prawnCurry = new Product(1, "Prawn curry", goan);
        SugarRecord.save(prawnCurry);
        Product sandw = new Product(2, "butter chicken sandwich", goan);
        SugarRecord.save(sandw);
        SugarRecord.save(new ProductParty(1,
                prawnCurry,
                awesomeRest,
                "Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices",
                80,
                ""));
        SugarRecord.save(new ProductParty(2,
                sandw,
                awesomeRest,
                "Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices",
                80,
                ""));

    }

}
