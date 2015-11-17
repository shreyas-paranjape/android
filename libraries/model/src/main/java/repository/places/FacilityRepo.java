package repository.places;

import com.orm.SugarRecord;

import java.util.List;

import model.places.Facility;

public class FacilityRepo {

    public static List<Facility> getAll() {
        return SugarRecord.listAll(Facility.class);
    }
}
