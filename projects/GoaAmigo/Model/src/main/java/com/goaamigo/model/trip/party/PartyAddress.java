package com.goaamigo.model.trip.party;

import com.goaamigo.model.trip.common.Address;
import com.goaamigo.model.trip.common.Role;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class PartyAddress implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "party_id")
    private Party party;

    @Column(name = "address_id")
    private Address address;

    @Column(name = "role_id")
    private Role role;

}
