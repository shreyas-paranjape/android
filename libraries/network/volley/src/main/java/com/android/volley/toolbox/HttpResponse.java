package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private final StatusLine statusLine;
    private final List<Header> headers;
    private HttpEntity entity;

    public HttpResponse(StatusLine responseStatus) {
        this.statusLine = responseStatus;
        this.headers = new ArrayList<>();
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public Header[] getHeaders() {
        return headers.toArray(new Header[headers.size()]);
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public void addHeader(Header h) {
        headers.add(h);
    }
}
