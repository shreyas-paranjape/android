package com.goaamigo.model.party;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Party implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "name")
    private String name;

}
