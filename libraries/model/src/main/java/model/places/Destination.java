package model.places;

import model.common.Location;
import com.orm.dsl.Column;

import java.io.Serializable;

public class Destination implements Serializable {

    private static final long serialVersionUID = 1l;

    @Column(name = "name")
    private String name;

    @Column(name = "location_id")
    private Location location;
}
