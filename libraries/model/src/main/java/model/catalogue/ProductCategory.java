package model.catalogue;

import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private ProductCategory parent;
    @Ignore
    private int productImage;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(String name, int productImage) {
        this.name = name;
        this.productImage = productImage;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }
}
