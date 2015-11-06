package com.goaamigo.model.trip.places;

import com.goaamigo.model.trip.common.Address;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Location implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "address_id")
    private Address address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "altitude")
    private double altitude;


}
