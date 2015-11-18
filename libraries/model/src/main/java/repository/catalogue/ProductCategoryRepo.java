package repository.catalogue;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class ProductCategoryRepo {

    public static ProductCategory getById(long productCategoryId) {
        return SugarRecord.findById(ProductCategory.class, productCategoryId);
    }

    public static List<ProductCategory> getAll() {
        return SugarRecord.listAll(ProductCategory.class);
    }

    public static List<ProductCategory> getImmediateChildren(long productCategoryId) {
        return SugarRecord.find(
                ProductCategory.class,
                "parent_id = ?",
                String.valueOf(productCategoryId));
    }

    public static List<ProductCategory> getAllChildren(long productCategoryId) {
        List<ProductCategory> allChildren = new ArrayList<>();
        List<ProductCategory> immediateChildren = getImmediateChildren(productCategoryId);
        allChildren.addAll(immediateChildren);
        for (ProductCategory aSubCat : immediateChildren) {
            allChildren.addAll(getAllChildren(aSubCat.getId()));
        }
        return allChildren;
    }

    public static List<Long> getAllChildrenId(long productCategoryId) {
        List<Long> allChildrenIds = new ArrayList<>();
        for (ProductCategory aCat : getAllChildren(productCategoryId)) {
            allChildrenIds.add(aCat.getId());
        }
        return allChildrenIds;
    }

}
