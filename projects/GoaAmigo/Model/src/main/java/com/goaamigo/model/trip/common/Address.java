package com.goaamigo.model.trip.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Address implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "lineOne")
    private String lineOne;

    @Column(name = "lineTwo")
    private String lineTwo;

    @Column(name = "locality")
    private String locality;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "postalCode")
    private String postalCode;



}
