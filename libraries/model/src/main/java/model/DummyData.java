package model;

import com.orm.SugarRecord;

import java.util.Arrays;

import model.catalogue.Product;
import model.catalogue.ProductCategory;
import model.party.Party;
import repository.catalogue.ProductCategoryRepo;

public class DummyData {


    public static void createDummyData(String app) {

        for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20})) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(i);
            productCategory.setParent(ProductCategoryRepo.getById(i - 1));
            productCategory.setName("Product Category : " + productCategory.getId());
            SugarRecord.save(productCategory);
        }


        for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
            Product product = new Product();
            product.setId(i);
            product.setName("Product : " + product.getId());
            product.setProductCategory(ProductCategoryRepo.getById(i));
            product.setDescription("Product Description: " + product.getId());
            product.setPrice(i * 1000);
            SugarRecord.save(product);
        }


        for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
            Party party = new Party();
            party.setId(i);
            party.setName("Party : " + party.getId());
            SugarRecord.save(party);
        }
    }

    private DummyData() {
        throw new IllegalStateException("");
    }
}
