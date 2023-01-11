package com.example.recipesApp;

import com.example.recipesApp.api.request.CreateIngredientRequest;
import com.example.recipesApp.data.model.IngredientEntity;

import java.time.LocalDateTime;
import java.util.List;

public class IngredientTestData {

    public static CreateIngredientRequest createIngredientRequest() {
        return new CreateIngredientRequestBuilderTest()
                .withName("Potato")
                .build();
    }

    public static IngredientEntity createIngredient() {
        return new IngredientModelBuilderTest()
                .withName("Potato")
                .build();
    }

    public static IngredientEntity createIngredientWithNameParam(String name) {
        return new IngredientModelBuilderTest()
                .withName(name)
                .build();
    }


    public static List<IngredientEntity> createIngredientList() {
        return createIngredientList(false);
    }

    public static List<IngredientEntity> createIngredientList(boolean withId) {
        IngredientEntity i1 = new IngredientModelBuilderTest()
                .withId(withId ? 10 : null)
                .withName("Potato")
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .build();

        IngredientEntity i2 = new IngredientModelBuilderTest()
                .withId(withId ? 11 : null)
                .withName("Tomato")
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .build();

        return List.of(i1, i2);
    }

}
