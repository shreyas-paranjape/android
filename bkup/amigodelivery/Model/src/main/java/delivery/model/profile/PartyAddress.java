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

    public PartyAddress() {
    }

    public PartyAddress(Party party, Address address) {
        this.party = party;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartyAddress that = (PartyAddress) o;

        if (party != null ? !party.equals(that.party) : that.party != null) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        int result = party != null ? party.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

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
