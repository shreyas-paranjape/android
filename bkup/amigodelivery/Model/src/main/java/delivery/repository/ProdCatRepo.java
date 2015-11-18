package delivery.repository;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import delivery.model.catalogue.ProductCategory;

public class ProdCatRepo {

    public static List<ProductCategory> getMenuCategories() {
        return SugarRecord.listAll(ProductCategory.class);
    }

    public static List<ProductCategory> getSubCategories(ProductCategory category) {
        if (category == null) {
            return new ArrayList<>();
        }
        return SugarRecord.find(ProductCategory.class,
                "parent_id = ?", String.valueOf(category.getId()));
    }

    public static ProductCategory save(ProductCategory category) {
        SugarRecord.save(category);
        return category;
    }
}
