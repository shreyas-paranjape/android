package model.catalogue;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Product implements Serializable, Cloneable {

    private static final long serialVersionUID = 1l;

    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "product_category_id")
    private ProductCategory productCategory;
    @Column(name = "detail")
    private ProductDetail detail;

    public Product() {
    }

    public Product(long id, String name, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
    }

    public Product(long id, String name, ProductCategory productCategory, ProductDetail detail) {
        this(id, name, productCategory);
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCategory=" + productCategory +
                ", detail=" + detail +
                '}';
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
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
