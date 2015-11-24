package com.goaamigo.model.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Location implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "admin_unit_id")
    private AdminUnit adminUnit;


}
