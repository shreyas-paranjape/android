package model.catalogue;

import model.common.Location;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductLocation implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "product_id")
    private Product product;

    @Column(name = "location_id")
    private Location location;

    //@Column(name = "role_id")
    //private Role role;


}
