package model.party;

import model.places.Facility;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class PartyFacility implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "party_id")
    private Party party;

    @Column(name = "facility_id")
    private Facility facility;

    //@Column(name = "role_id")
    //private Role role;

}
