package com.goaamigo.model.trip.order;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Order implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "placed_at")
    private long placedAt;




}
