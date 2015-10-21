package delivery.model.profile;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import delivery.model.common.Comm;

@Table
public class PartyComm {

    private long id;
    @Column(name = "party_id")
    private Party party;
    @Column(name = "comm_id")
    private Comm comm;

    public PartyComm() {
    }

    public PartyComm(Party party, Comm comm) {
        this.party = party;
        this.comm = comm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartyComm partyComm = (PartyComm) o;

        if (party != null ? !party.equals(partyComm.party) : partyComm.party != null) return false;
        return !(comm != null ? !comm.equals(partyComm.comm) : partyComm.comm != null);

    }

    @Override
    public int hashCode() {
        int result = party != null ? party.hashCode() : 0;
        result = 31 * result + (comm != null ? comm.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Comm getComm() {
        return comm;
    }

    public void setComm(Comm comm) {
        this.comm = comm;
    }
}
