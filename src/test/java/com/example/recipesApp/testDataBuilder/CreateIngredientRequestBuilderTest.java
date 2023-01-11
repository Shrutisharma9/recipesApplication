package com.example.recipesApp.testDataBuilder;

import com.example.recipesApp.api.request.CreateIngredientRequest;

public class CreateIngredientRequestBuilderTest {

    private String name;

    public CreateIngredientRequest build() {
        return new CreateIngredientRequest(name);
    }

    public CreateIngredientRequestBuilderTest withName(String firstName) {
        this.name = firstName;
        return this;
    }

}
