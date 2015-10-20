package com.goaamigo.model.trip;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Event implements Serializable {

    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "day_id")
    private Day day;

    public Event() {
    }

    public Event(String name) {
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
}
