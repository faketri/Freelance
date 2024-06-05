package com.hivework.domain.exceptions;

import java.util.Map;

public class AppErrorArray {
    private int statusCode;
    private Map<String, String> message;

    public AppErrorArray() {
    }

    public AppErrorArray(int statusCode, Map<String, String> message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
