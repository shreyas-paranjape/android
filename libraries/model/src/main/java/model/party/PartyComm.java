package model.party;

import com.orm.dsl.Column;

import java.io.Serializable;

public class PartyComm implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "party_id")
    private Party party;

    @Column(name = "email")
    private String email;

    @Column(name = "mobileNumber")
    private String mobileNumber;
}
