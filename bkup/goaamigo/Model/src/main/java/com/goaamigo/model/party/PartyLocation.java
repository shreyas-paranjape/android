package com.goaamigo.model.party;

import com.goaamigo.model.common.Location;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class PartyLocation implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "party_id")
    private Party party;

    @Column(name = "location_id")
    private Location location;


}
