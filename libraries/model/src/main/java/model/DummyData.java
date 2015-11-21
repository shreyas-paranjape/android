package model;

import com.orm.SugarRecord;

import java.util.Arrays;

import model.catalogue.Product;
import model.catalogue.ProductCategory;
import model.party.Party;
import repository.catalogue.ProductCategoryRepo;

public class DummyData {


    public static void createDummyData(String app) {
        if ("rest.order".equals(app)) {

            /************************************** Categories ***********************************/
            int pc_i = 0;
            ProductCategory food = new ProductCategory();
            food.setId(pc_i);
            food.setName("Food");
            SugarRecord.save(food);
            for (String name : new String[]{"Indian", "Oriental", "Pasta", "Barbeque"}) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(pc_i);
                productCategory.setParent(food);
                productCategory.setName(name);
                SugarRecord.save(productCategory);
                pc_i++;
            }

            ProductCategory drinks = new ProductCategory();
            drinks.setId(pc_i);
            drinks.setName("Drinks");
            SugarRecord.save(drinks);
            pc_i++;

            for (String name : new String[]{"Wines"}) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(pc_i);
                productCategory.setParent(drinks);
                productCategory.setName(name);
                SugarRecord.save(productCategory);
                pc_i++;
            }


            ProductCategory desserts = new ProductCategory();
            desserts.setId(pc_i);
            desserts.setName("Desserts");
            SugarRecord.save(desserts);
            pc_i++;

            for (String name : new String[]{"Ice cream"}) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(pc_i);
                productCategory.setParent(desserts);
                productCategory.setName(name);
                SugarRecord.save(productCategory);
                pc_i++;
            }

            /************************************** Products ***********************************/
            int prod_i = 0;
            for (String name : new String[]{"Ice cream"}) {
                Product product = new Product();
                product.setId(prod_i);
                product.setName(name);
                product.setProductCategory(ProductCategoryRepo.getById(prod_i));
                //product.setDescription("Product Description: " + product.getId());
                //product.setPrice(prod_i * 1000);
                //product.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(product);
            }

        } else {
            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3})) {
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
                //product.setDescription("Product Description: " + product.getId());
                //product.setPrice(i * 1000);
                //product.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(product);
            }


            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                Party party = new Party();
                party.setId(i);
                party.setName("Party : " + party.getId());
                SugarRecord.save(party);
            }
        }


    }

    private DummyData() {
        throw new IllegalStateException("");
    }
}
