package com.query;

import java.io.Serializable;

public class Query implements Serializable {

    private final String[] projection;
    private final Expression selection;
    private final String[] order;
    private final Limit limit;

    public Query(String[] projection, Expression selection,
                 String[] order, Limit limit) {
        this.projection = projection;
        this.selection = selection;
        this.order = order;
        this.limit = limit;
    }

    public String[] getProjection() {
        return projection;
    }

    public Expression getSelection() {
        return selection;
    }

    public String[] getOrder() {
        return order;
    }

    public Limit getLimit() {
        return limit;
    }

}
