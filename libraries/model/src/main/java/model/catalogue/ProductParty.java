package model.catalogue;

import model.party.Party;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class ProductParty implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "product_id")
    private Product product;

    @Column(name = "party_id")
    private Party party;

    //@Column(name = "role_id")
    //private Role role;
}
