package com.goaamigo.model.order;

import com.goaamigo.model.party.Party;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Order implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "party_id")
    private Party party;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    @Column(name = "created_stamp")
    private long created_stamp;

    @Column(name = "created_stamp")
    private long updated_stamp;

}
