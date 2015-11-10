package model.trip;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

@Table
public class Day implements Serializable {

    private long id;

    @Column(name = "name")
    private String name;
    private List<Event> events;

    public Day(List<Event> events, String name) {
        this.events = events;
        this.name = name;
    }

    public Day() {
    }

    public Day(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
