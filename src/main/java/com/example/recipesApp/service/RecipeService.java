package com.example.recipesApp.service;

import com.example.recipesApp.api.request.CreateRecipeRequest;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.data.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RecipeService {
    private final RecipesRepository recipeRepository;
    private final IngredientService ingredientService;

    @Autowired
    public RecipeService(RecipesRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    public Integer createRecipe(CreateRecipeRequest createRecipeRequest) {
        Set<IngredientEntity> ingredients = Optional.ofNullable(createRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        RecipesEntity recipe = new RecipesEntity();
        recipe.setName(createRecipeRequest.getName());
        recipe.setInstructions(createRecipeRequest.getInstructions());
        recipe.setType(createRecipeRequest.getType());
        recipe.setNumberOfServings(createRecipeRequest.getNumberOfServings());
        recipe.setRecipeIngredients(ingredients);

        RecipesEntity createdRecipe = recipeRepository.save(recipe);

        return createdRecipe.getId();
    }
}