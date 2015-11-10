package com.query;

import java.io.Serializable;

public class Predicate implements Expression, Serializable {

    private static final long serialVersionUID = 1l;

    private final String column;
    private final String operator;
    private final String value;

    public Predicate(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }


    public String getColumn() {
        return column;
    }


    public String getOperator() {
        return operator;
    }


    public String getValue() {
        return value;
    }

}
