package in.co.foodamigo.admin.module.catalogue.model;


import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.NotNull;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

@Table
public class ProductCategory implements Serializable {

    private long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private ProductCategory parent;


    public ProductCategory() {
    }

    public ProductCategory(long id, String title) {
        this.id = id;
        this.name = title;
    }

    public ProductCategory(long id, String title, ProductCategory parent) {
        this(id, title);
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }

    public String getName() {
        return name;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }

    public List<Product> getProducts() {
        return SugarRecord.find(Product.class,
                "product_category_id = ?", String.valueOf(getId()));
    }

    public List<ProductCategory> getSubCategories() {
        return SugarRecord.find(ProductCategory.class,
                "parent_id = ?", String.valueOf(getId()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
