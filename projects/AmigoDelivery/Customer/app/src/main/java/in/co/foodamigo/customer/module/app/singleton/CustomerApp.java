package in.co.foodamigo.customer.module.app.singleton;

import android.app.Application;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.util.FontsOverride;

import delivery.model.catalogue.Product;
import delivery.model.catalogue.ProductCategory;
import delivery.model.catalogue.ProductParty;
import delivery.model.profile.Party;
import in.co.foodamigo.customer.module.order.controller.CurrentOrderManager;

public class CustomerApp extends Application {

    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();

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

    public CurrentOrderManager getCurrentOrderManager() {
        return currentOrderManager;
    }

    private void createDummyData() {

        // Party
        Party carasid = new Party(1, "carasid", "");
        SugarRecord.save(carasid);
        Party canape = new Party(1, "canape", "");
        SugarRecord.save(carasid);
        Party alua = new Party(1, "alua", "");
        SugarRecord.save(carasid);

        String[] categories = new String[]{"Snacks", "Goan", "Desi", "Oriental",
                "Rice", "Breads", "Dessert"};

        // Category
        ProductCategory Snacks = new ProductCategory(1, "Snacks");
        SugarRecord.save(Snacks);
        ProductCategory Goan = new ProductCategory(2, "Goan");
        SugarRecord.save(Goan);
        /*ProductCategory Desi = new ProductCategory(3, "Desi");
        SugarRecord.save(Desi);
        ProductCategory Oriental = new ProductCategory(4, "Oriental");
        SugarRecord.save(Oriental);
        ProductCategory Rice = new ProductCategory(5, "Rice");
        SugarRecord.save(Rice);
        ProductCategory Dessert = new ProductCategory(6, "Dessert");
        SugarRecord.save(Dessert);
        ProductCategory Breads = new ProductCategory(7, "Breads");
        SugarRecord.save(Breads);*/


        // Product
        Product prawnCurry = new Product(1, "Prawn curry", Goan);
        SugarRecord.save(prawnCurry);
        Product sandw = new Product(2, "butter chicken sandwich", Snacks);
        SugarRecord.save(sandw);
        Product bc = new Product(3, "butter chicken", Snacks);
        SugarRecord.save(bc);
        Product cb = new Product(4, "chicken biryani", Goan);
        SugarRecord.save(cb);


        // ProductParty
        SugarRecord.save(new ProductParty(1,
                prawnCurry,
                carasid,
                "Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices",
                80,
                "file:///android_asset/Prawn curry.jpg"));
        SugarRecord.save(new ProductParty(2,
                sandw,
                carasid,
                "A whole new twist to Butter chicken by putting it between perfectly toasted bread",
                150,
                "file:///android_asset/but chick sand.jpg"));
        SugarRecord.save(new ProductParty(3,
                bc,
                alua,
                "Tandoori chicken in creamy gravy with sour and sweet undertones; a real treat",
                200,
                "file:///android_asset/but chick.jpg"));
        SugarRecord.save(new ProductParty(4,
                cb,
                canape,
                "Traditional Awadhi delicacy made with basmati rice, chicken, spices and onion",
                200,
                "file:///android_asset/but chick.jpg"));
    }
}

/*
Supplier awesomeRest = objectStore.createObject(Supplier.class);
        awesomeRest.setId(1);
        awesomeRest.setName("My fancy rest");

        Product prawnCurry = objectStore.createObject(Product.class);
        prawnCurry.setId(1);
        prawnCurry.setName("Prawn Curry");
        prawnCurry.setDescription("Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices");
        prawnCurry.setRate(80);
        prawnCurry.setProductSupplier(awesomeRest);
        prawnCurry.setDish_url("file:///android_asset/Prawn curry.jpg");


        Product chickenBiryani = objectStore.createObject(Product.class);
        chickenBiryani.setId(2);
        chickenBiryani.setName("Chicken biryani");
        chickenBiryani.setDescription("Traditional Awadhi delicacy made with basmati rice, chicken, spices and onion");
        chickenBiryani.setRate(160);
        chickenBiryani.setDish_url("file:///android_asset/chick bir.JPG");
        chickenBiryani.setProductSupplier(awesomeRest);

        Product palakPaneer = objectStore.createObject(Product.class);
        palakPaneer.setId(3);
        palakPaneer.setName("Palak panner");
        palakPaneer.setDescription("A much loved vegetarian dish with curd cheese in a thick, mild spinach puree");
        palakPaneer.setRate(150);
        palakPaneer.setDish_url("file:///android_asset/palak paneer.JPG");
        palakPaneer.setProductSupplier(awesomeRest);

        Product jeeraRice = objectStore.createObject(Product.class);
        jeeraRice.setId(4);
        jeeraRice.setName("Jeera rice");
        jeeraRice.setDescription("Basmati rice cooked with cumin seeds; tastes great with most Indian curries");
        jeeraRice.setRate(100);
        jeeraRice.setDish_url("file:///android_asset/jeera rice.jpg");
        jeeraRice.setProductSupplier(awesomeRest);


        Product butterChicken = objectStore.createObject(Product.class);
        butterChicken.setId(5);
        butterChicken.setName("Butter chicken");
        butterChicken.setDescription("Tandoori chicken in creamy gravy with sour and sweet undertones; a real treat");
        butterChicken.setRate(180);
        butterChicken.setDish_url("file:///android_asset/but chick.jpg");
        butterChicken.setProductSupplier(awesomeRest);

        Product butterChickenSand = objectStore.createObject(Product.class);
        butterChickenSand.setId(6);
        butterChickenSand.setName("Butter chicken sandwich");
        butterChickenSand.setDescription("A whole new twist to Butter chicken by putting it between perfectly toasted bread");
        butterChickenSand.setRate(120);
        butterChickenSand.setProductSupplier(awesomeRest);
        butterChickenSand.setDish_url("file:///android_asset/but chick sand.jpg");


        Product lahoriChicken = objectStore.createObject(Product.class);
        lahoriChicken.setId(7);
        lahoriChicken.setName("Lahori chicken");
        lahoriChicken.setDescription("Chicken cooked in a rich spicy gravy with a heavy dose of ginger and garlic");
        lahoriChicken.setRate(180);
        lahoriChicken.setProductSupplier(awesomeRest);
        lahoriChicken.setDish_url("file:///android_asset/Lahori Chicken.jpg");


        Product chicSukha = objectStore.createObject(Product.class);
        chicSukha.setId(8);
        chicSukha.setName("Chicken Sukha");
        chicSukha.setDescription("Goan traditional dish with chicken");
        chicSukha.setRate(120);
        chicSukha.setProductSupplier(awesomeRest);
        chicSukha.setDish_url("file:///android_asset/chic sukha.jpg");

        Product dalTadka = objectStore.createObject(Product.class);
        dalTadka.setId(9);
        dalTadka.setName("Dal tadka");
        dalTadka.setDescription("Quintessential Indian yellow lentil curry; mild gravy infused with pan-fried spices");
        dalTadka.setRate(100);
        dalTadka.setProductSupplier(awesomeRest);
        dalTadka.setDish_url("file:///android_asset/dal tadka.jpg");

        Product raraMutton = objectStore.createObject(Product.class);
        raraMutton.setId(10);
        raraMutton.setName("Rara mutton");
        raraMutton.setDescription("succulent pieces of lamb cooked with mutton mince and a myriad of spices");
        raraMutton.setRate(200);
        raraMutton.setProductSupplier(awesomeRest);
        raraMutton.setDish_url("file:///android_asset/Rara Mutton.JPG");


        String[] categories = new String[]{"Snacks", "Goan", "Desi", "Oriental", "Rice", "Breads", "Dessert"};

        ProductCategory food = objectStore.createObject(ProductCategory.class);
        food.setName("food");
        food.setId(1);

        for (int i = 0; i < categories.length; i++) {
            ProductCategory prodCat = objectStore.createObject(ProductCategory.class);
            prodCat.setId(i + 2);
            prodCat.setName(categories[i]);
            prodCat.getProducts().add(prawnCurry);
            prodCat.getProducts().add(palakPaneer);
            prodCat.getProducts().add(jeeraRice);
            prodCat.getProducts().add(butterChicken);
            prodCat.getProducts().add(butterChickenSand);
            prodCat.getProducts().add(raraMutton);
            prodCat.getProducts().add(chickenBiryani);
            prodCat.getProducts().add(lahoriChicken);
            prodCat.getProducts().add(chicSukha);
            prodCat.getProducts().add(dalTadka);
            food.getSubCategories().add(prodCat);
        }
 */
