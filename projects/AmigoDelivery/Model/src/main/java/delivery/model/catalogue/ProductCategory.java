package delivery.model.catalogue;


import com.orm.dsl.Column;
import com.orm.dsl.NotNull;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductCategory implements Serializable {

    private long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "parent_id")
    private ProductCategory parent;

    public ProductCategory() {
    }

    public ProductCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory(long id, String name, ProductCategory parent) {
        this(id, name);
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



    public long getId() {
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
}
