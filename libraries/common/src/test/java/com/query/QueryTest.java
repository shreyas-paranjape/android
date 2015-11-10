package com.query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;


public class QueryTest extends TestCase {

    public void testToJSON() {
        Gson gson = new GsonBuilder().create();

        Limit limit = new Limit(10, 5);
        Expression e = new Predicate("x", "<", "20");
        Query q = new Query(new String[]{"a", "b"}, e, new String[]{"x", "y"}, limit);

        String qj = gson.toJson(q);
        assertEquals(qj, "{\"projection\":[\"a\",\"b\"]," +
                "\"selection\":{\"column\":\"x\",\"operator\":\"\\u003c\",\"value\":\"20\"}," +
                "\"order\":[\"x\",\"y\"]," +
                "\"limit\":{\"startId\":10,\"count\":5}}");
    }
}