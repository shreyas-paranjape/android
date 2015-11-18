package delivery.service;

import java.util.ArrayList;
import java.util.List;

import delivery.model.catalogue.Product;
import delivery.model.catalogue.ProductCategory;

public class CatalogueService {

    public static List<ProductCategory> getProductCat(boolean withSub,
                                                      boolean withProd,
                                                      boolean withParent) {
        return new ArrayList<>();
    }

    public static List<Product> getProductForCat(ProductCategory category) {
        return new ArrayList<>();
    }
}
