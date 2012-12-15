package com.example.niko.api;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

public class Response {
    private boolean success;
    private Exception exception;
    private HttpResponse response;

    public Response(Exception e) {
        success = false;
        exception = e;
        response = null;
    }

    public Response(HttpResponse response) {
        this.response = response;
        success = (HttpStatus.SC_OK == response.getStatusLine().getStatusCode());
    }

    public boolean isSuccess() {
        return success;
    }

    public Exception getException() {
        return exception;
    }

    public HttpResponse getResponse() {
        return response;
    }
}
