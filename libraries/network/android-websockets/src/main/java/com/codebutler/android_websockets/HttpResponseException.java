package com.codebutler.android_websockets;

public class HttpResponseException extends RuntimeException {

    public HttpResponseException(int statusCode, String s) {
        throw new RuntimeException(s);
    }

    public HttpResponseException(String s) {
        throw new RuntimeException(s);
    }

}
