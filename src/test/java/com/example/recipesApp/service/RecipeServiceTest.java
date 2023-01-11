package com.example.recipesApp.service;

import com.example.recipesApp.api.request.CreateRecipeRequest;
import com.example.recipesApp.api.request.RecipeSearchRequest;
import com.example.recipesApp.api.request.UpdateRecipeRequest;
import com.example.recipesApp.config.MessageProvider;
import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.data.repository.RecipesRepository;
import com.example.recipesApp.exception.NotFoundException;
import com.example.recipesApp.search.RecipeSpecificationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipesRepository recipesRepository;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private MessageProvider messageProvider;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void createRecipeTest(){
        CreateRecipeRequest request = new CreateRecipeRequest("Noodles", "OTHER", 3, null, "Soe instructions");

        RecipesEntity response = new RecipesEntity();
        response.setName("Name");
        response.setInstructions("Some instructions");
        response.setNumberOfServings(3);

        when(recipesRepository.save(any(RecipesEntity.class))).thenReturn(response);

        Integer id = recipeService.createRecipe(request);

        assertThat(id).isSameAs(response.getId());
    }

    @Test
    public void updateRecipeTest(){
        RecipesEntity response = new RecipesEntity();
        response.setName("Name");
        response.setType("OTHER");
        response.setNumberOfServings(3);
        response.setId(4);

        UpdateRecipeRequest request = new UpdateRecipeRequest(1, "Noodles", "OTHER", 3, null, "some instructions");

        when(recipesRepository.save(any(RecipesEntity.class))).thenReturn(response);
        when(recipesRepository.findById((anyInt()))).thenReturn(Optional.of(response));

        recipeService.updateRecipe(request);
    }

    @Test(expected = NotFoundException.class)
    public void updateNonExistingRecipeTest(){
        UpdateRecipeRequest request = new UpdateRecipeRequest(1,"Pizza", "OTHER", 1, null, "instructions");

        when(recipesRepository.findById(anyInt())).thenReturn(Optional.empty());

        recipeService.updateRecipe(request);
    }

    @Test
    public void deleteRecipeTest(){
        when(recipesRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(recipesRepository).deleteById(anyInt());

        recipeService.deleteRecipe(1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNonExistingRecipeTest(){
        when(recipesRepository.existsById(anyInt())).thenReturn(false);

        recipeService.deleteRecipe(1);
    }

    @Test(expected = NotFoundException.class)
    public void searchCriteriaNonExistingValueTest(){
        RecipeSearchRequest recipeSearchRequest = mock(RecipeSearchRequest.class);
        RecipeSpecificationBuilder builder = mock(RecipeSpecificationBuilder.class);
        Pageable pageable = mock(Pageable.class);

        recipeService.findBySearchCriteria(recipeSearchRequest,0,5);
    }
}