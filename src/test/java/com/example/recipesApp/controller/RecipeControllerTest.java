package com.example.recipesApp.controller;

import com.example.recipesApp.api.request.CreateRecipeRequest;
import com.example.recipesApp.api.request.UpdateRecipeRequest;
import com.example.recipesApp.api.response.CreateEntityResponse;
import com.example.recipesApp.api.response.RecipeResponse;
import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    public void createRecipesTest(){
        CreateRecipeRequest request = new CreateRecipeRequest("Noodles", "OTHER", 6, null, "instructions");

        when(recipeService.createRecipe(any(CreateRecipeRequest.class))).thenReturn(1);

        CreateEntityResponse response = recipeController.createRecipe(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void listRecipeTest(){
        RecipesEntity recipe = new RecipesEntity();
        recipe.setId(5);
        recipe.setName("Pasta");

        when(recipeService.getRecipeById(anyInt())).thenReturn(recipe);

        RecipeResponse response = recipeController.getRecipe(5);

        assertThat(response.getId()).isSameAs(recipe.getId());
        assertThat(response.getName()).isSameAs(recipe.getName());
    }

    @Test
    public void listRecipesTest(){
        RecipesEntity recipe1 = new RecipesEntity();
        recipe1.setId(5);
        recipe1.setName("Pasta");

        RecipesEntity recipe2 = new RecipesEntity();
        recipe2.setId(6);
        recipe2.setName("Noodles");

        RecipesEntity recipe3 = new RecipesEntity();
        recipe3.setId(7);
        recipe3.setName("Lasagna");

        List<RecipesEntity> storedRecipesList = new ArrayList<>();
        storedRecipesList.add(recipe1);
        storedRecipesList.add(recipe2);
        storedRecipesList.add(recipe3);

        when(recipeService.getRecipeList(anyInt(), anyInt())).thenReturn(storedRecipesList);

        List<RecipeResponse> recipesList = recipeController.getRecipeList(anyInt(), anyInt());

        assertThat(storedRecipesList.size()).isSameAs(recipesList.size());
        assertThat(storedRecipesList.get(0).getId()).isSameAs(recipesList.get(0).getId());
        assertThat(storedRecipesList.get(1).getId()).isSameAs(recipesList.get(1).getId());
        assertThat(storedRecipesList.get(2).getId()).isSameAs(recipesList.get(2).getId());

    }

    @Test
    public void updateRecipeTest(){
        RecipesEntity recipe = new RecipesEntity();
        recipe.setName("Name");
        recipe.setType("OTHER");
        recipe.setInstructions("none");

        doNothing().when(recipeService).updateRecipe(any());
        recipe.setName("Recipe Name");

        UpdateRecipeRequest request = new UpdateRecipeRequest(1, "Noodles", "OTHER", 4, null, "some instructions");

        recipeController.updateRecipe(request);
    }

    @Test
    public void deleteRecipeTest(){
        doNothing().when(recipeService).deleteRecipe(anyInt());
        recipeController.deleteRecipe(5);
    }

}