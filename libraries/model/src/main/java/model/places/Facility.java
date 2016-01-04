package model.places;

import model.common.Location;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Facility implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location_id")
    private Location location;

    public Facility(String name, Location location) {
        this.name = name;
        this.location = location;
    }
}
