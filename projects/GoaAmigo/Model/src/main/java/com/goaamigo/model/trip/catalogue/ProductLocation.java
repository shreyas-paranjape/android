package com.goaamigo.model.trip.catalogue;

import com.goaamigo.model.trip.common.Role;
import com.goaamigo.model.trip.places.Facility;
import com.orm.dsl.Column;

import java.io.Serializable;

public class ProductLocation implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "product_id")
    private Product product;

    @Column(name = "facility_id")
    private Facility facility;

    @Column(name = "role_id")
    private Role role;


}
