package com.query;

import java.io.Serializable;
import java.util.List;

public class LogicalExpression implements Expression, Serializable {

    private static final long serialVersionUID = 1l;

    private String operator;
    private List<Predicate> predicates;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }
}
