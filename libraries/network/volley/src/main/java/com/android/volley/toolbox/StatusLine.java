package com.android.volley.toolbox;

public class StatusLine {

    private int statusCode;
    private String reasonPhrase;

    public StatusLine(int responseCode, String responseMessage) {
        this.statusCode = responseCode;
        this.reasonPhrase = responseMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    int getStatusCode(){
        return statusCode;
    }

    String getReasonPhrase(){
        return reasonPhrase;
    }
}
