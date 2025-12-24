package com.edu.ec2.dto;

public class HelloResponse {

    private String message;

    public HelloResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

