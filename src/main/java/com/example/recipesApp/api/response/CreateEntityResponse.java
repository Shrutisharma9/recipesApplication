package com.example.recipesApp.api.response;

public class CreateEntityResponse {
    private int id;

    public CreateEntityResponse(int id) {
        this.id = id;
    }

    public CreateEntityResponse() {
    }

    public int getId() {
        return id;
    }
}