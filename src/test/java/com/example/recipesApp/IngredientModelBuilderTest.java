package com.example.recipesApp;

import com.example.recipesApp.data.model.IngredientEntity;

import java.time.LocalDateTime;

public class IngredientModelBuilderTest {


    private Integer id;
    private String ingredientName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public IngredientEntity build() {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(id);
        ingredient.setIngredient(ingredientName);
        ingredient.setCreatedAt(createdAt);
        ingredient.setUpdatedAt(updatedAt);

        return ingredient;
    }
    public IngredientModelBuilderTest withId(Integer id) {
        this.id = id;
        return this;
    }

    public IngredientModelBuilderTest withName(String name) {
        this.ingredientName = name;
        return this;
    }
    public IngredientModelBuilderTest withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public IngredientModelBuilderTest withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }


}
