package delivery.repository;

import java.util.ArrayList;
import java.util.List;

import delivery.model.common.Address;
import delivery.model.common.GeoLocation;
import delivery.model.profile.Party;

public class PartyRepo {

    public static List<Address> getAddresses(Party party) {
        //List<PartyAddress> partyAddresses = SugarRecord.find(PartyAddress.class,
        //        "party_id = ?", String.valueOf(party.getId()));
        List<Address> res = new ArrayList<>();
        //for (PartyAddress partyAddress : partyAddresses) {
        //    res.add(partyAddress.getAddress());
        //}
        res.add(new Address("settertertsgterst", "ewatewtw4e5t34eftwa45q", new GeoLocation()));
        return res;
    }
}
