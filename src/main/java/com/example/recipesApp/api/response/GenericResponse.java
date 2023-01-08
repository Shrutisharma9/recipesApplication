package com.example.recipesApp.api.response;

import java.util.Objects;

public class GenericResponse {

    private final String message;

    public GenericResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}