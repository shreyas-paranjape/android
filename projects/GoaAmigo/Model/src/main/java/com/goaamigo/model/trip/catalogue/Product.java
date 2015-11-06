package com.goaamigo.model.trip.catalogue;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Product implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_category_id")
    private ProductCategory productCategory;

    private String productLocation;
    private String productRating;
    private String productDiscount;
    private String productPrice;
    private int productImage;

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Product(String name, String productLocation, String productRating, String productDiscount, String productPrice, int productImage) {
        this.name = name;
        this.productLocation = productLocation;
        this.productRating = productRating;
        this.productDiscount = productDiscount;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public Product() {
    }

    public Product(long id, String name, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
