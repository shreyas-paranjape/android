package repository.party;

import com.orm.SugarRecord;

import java.util.List;

import model.party.Party;

public class PartyRepo {

    public static List<Party> getAll() {
        return SugarRecord.listAll(Party.class);
    }

}
