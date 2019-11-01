package org.alv.entity;

// Clase for handle the JSON request with a message encoded in b64
public class AlvRequest {
    private String message;

    public AlvRequest() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}