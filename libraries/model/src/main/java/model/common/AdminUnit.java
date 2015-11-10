package model.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class AdminUnit implements Serializable {

    private static final long serialVersionUID = 1l;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private AdminUnit parent;
}
