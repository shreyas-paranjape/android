package delivery.model.profile;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import delivery.model.common.Address;

@Table
public class PartyAddress {

    @Column(name = "party_id")
    private Party party;
    @Column(name = "address_id")
    private Address address;

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
