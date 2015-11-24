package com.goaamigo.model.catalogue;

import com.orm.dsl.Column;
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


}
