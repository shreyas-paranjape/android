package model;

import com.orm.SugarRecord;

import java.util.Arrays;

import model.catalogue.Product;
import model.catalogue.ProductCategory;
import model.catalogue.ProductDetail;
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
                ProductDetail detail = new ProductDetail();
                detail.setId(prod_i);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(prod_i * 1000);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
            }

        } else {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(1);
            productCategory.setParent(ProductCategoryRepo.getById(0));
            productCategory.setName("Stay");
            SugarRecord.save(productCategory);
            productCategory.setId(2);
            productCategory.setParent(ProductCategoryRepo.getById(1));
            productCategory.setName("Restaurant");
            SugarRecord.save(productCategory);
            productCategory.setId(3);
            productCategory.setParent(ProductCategoryRepo.getById(1));
            productCategory.setName("Activities");
            SugarRecord.save(productCategory);
            productCategory.setId(4);
            productCategory.setParent(ProductCategoryRepo.getById(2));
            productCategory.setName("LocalTransport_taxi");
            SugarRecord.save(productCategory);
            productCategory.setId(5);
            productCategory.setParent(ProductCategoryRepo.getById(3));
            productCategory.setName("LocalTransport_rental");

            SugarRecord.save(productCategory);

            Product product = new Product();
            int p_id = 0;
            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                product.setId(p_id);
                product.setName(ProductCategoryRepo.getById(1).getName() + " Product " + product.getId());
                product.setProductCategory(ProductCategoryRepo.getById(1));
                ProductDetail detail = new ProductDetail();
                detail.setId(i);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(i * 1000);
//                detail.setRating(1+i/5);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
                p_id++;
            }

            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                product.setId(p_id);
                product.setName(ProductCategoryRepo.getById(2).getName() + " Product " + product.getId());
                product.setProductCategory(ProductCategoryRepo.getById(2));
                ProductDetail detail = new ProductDetail();
                detail.setId(i);
//                detail.setRating(1 + i / 5);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(i * 1000);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
                p_id++;
            }
            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                product.setId(p_id);
                product.setName(ProductCategoryRepo.getById(3).getName() + " Product " + product.getId());
                product.setProductCategory(ProductCategoryRepo.getById(3));
                ProductDetail detail = new ProductDetail();
                detail.setId(i);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(i * 1000);
//                detail.setRating(i);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
                p_id++;
            }
            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                product.setId(p_id);
                product.setName(ProductCategoryRepo.getById(4).getName() + " Product " + product.getId());
                product.setProductCategory(ProductCategoryRepo.getById(4));
                ProductDetail detail = new ProductDetail();
                detail.setId(i);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(i * 1000);
//                detail.setRating(i);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
                p_id++;
            }
            for (Integer i : Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})) {
                product.setId(p_id);
                product.setName(ProductCategoryRepo.getById(5).getName() + " Product " + product.getId());
                product.setProductCategory(ProductCategoryRepo.getById(5));
                ProductDetail detail = new ProductDetail();
                detail.setId(i);
//                detail.setRating(i);
                detail.setDescription("Product Description: " + product.getId());
                detail.setPrice(i * 1000);
                detail.setImageUrl("file:///android_asset/1.jpg");
                SugarRecord.save(detail);
                product.setDetail(detail);
                SugarRecord.save(product);
                p_id++;
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
