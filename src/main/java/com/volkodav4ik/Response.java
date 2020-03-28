package com.volkodav4ik;

public class Response {

    String body;
    Exception exception;
    int responseCode;

    public String getBody() {
        return body;
    }

    public Exception getException() {
        return exception;
    }

    public int getResponseCode() {
        return responseCode;
    }

}