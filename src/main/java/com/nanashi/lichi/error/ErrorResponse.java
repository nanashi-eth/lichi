package com.nanashi.lichi.error;

public class ErrorResponse {
    private String message;

    // Constructor, getters y setters
    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
