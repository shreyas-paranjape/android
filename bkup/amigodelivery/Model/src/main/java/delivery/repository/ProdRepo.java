package delivery.repository;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import delivery.model.catalogue.Product;
import delivery.model.catalogue.ProductCategory;
import delivery.model.catalogue.ProductParty;

public class ProdRepo {

    public static List<ProductParty> findByCategory(ProductCategory category) {
        if (category == null) {
            return new ArrayList<>();
        }
        List<ProductParty> productParties = new ArrayList<>();
        List<Product> products =
                SugarRecord.find(Product.class,
                        "product_category_id = ?", String.valueOf(category.getId()));
        for (Product product : products) {
            productParties.addAll(
                    SugarRecord.find(ProductParty.class,
                            "product_id = ?", String.valueOf(product.getId())));
        }
        return productParties;
    }


}
