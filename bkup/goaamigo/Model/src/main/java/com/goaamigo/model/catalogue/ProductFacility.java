package com.goaamigo.model.catalogue;

import com.goaamigo.model.places.Facility;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductFacility implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "product_id")
    private Product product;

    @Column(name = "facility_id")
    private Facility facility;

    //@Column(name = "role_id")
    //private Role role;

}
