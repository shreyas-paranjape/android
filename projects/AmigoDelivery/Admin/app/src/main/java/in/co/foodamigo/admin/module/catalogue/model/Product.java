package in.co.foodamigo.admin.module.catalogue.model;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Product implements Serializable {

    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "product_category_id")
    private ProductCategory category;
    @Column(name = "supplier_id")
    private Supplier productSupplier;


    public Product() {
    }

    public Product(long id, String title, ProductCategory category) {
        this.id = id;
        this.name = title;
        this.category = category;
    }

    public Product(long id, String title, String description,
                   double rate, String imageUrl,
                   ProductCategory category, Supplier productSupplier) {
        this.id = id;
        this.name = title;
        this.description = description;
        this.price = rate;
        this.imageUrl = imageUrl;
        this.category = category;
        this.productSupplier = productSupplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category=" + category +
                '}';
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Supplier getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(Supplier productSupplier) {
        this.productSupplier = productSupplier;
    }
}
