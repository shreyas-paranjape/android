package repository.catalogue;

import com.orm.SugarRecord;

import java.util.List;

import model.catalogue.Product;
import repository.Util;

public class ProductRepo {

    public static List<Product> getAll() {
        return SugarRecord.listAll(Product.class);
    }

    public static List<Product> getForCategoryOnly(long productCategoryId) {
        return SugarRecord.find(
                Product.class,
                "product_category_id = ?",
                String.valueOf(productCategoryId));
    }

    public static List<Product> getForCategoryWithSub(long productCategoryId) {
        List<Product> res = getForCategoryOnly(productCategoryId);
        List<Long> childIds = ProductCategoryRepo.getAllChildrenId(productCategoryId);
        if (childIds.size() > 0) {
            res.addAll(SugarRecord.find(
                    Product.class,
                    "product_category_id in (" + Util.makePlaceholders(childIds.size()) + ")",
                    Util.listToStringArray(childIds)));
        }
        return res;
    }
}
