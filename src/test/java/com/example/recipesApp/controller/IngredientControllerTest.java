package com.example.recipesApp.controller;

import com.example.recipesApp.api.request.CreateIngredientRequest;
import com.example.recipesApp.api.response.CreateEntityResponse;
import com.example.recipesApp.api.response.IngredientResponse;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.service.IngredientService;
import com.example.recipesApp.testDataBuilder.IngredientTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @Test
    public void createIngredientsTest(){

        when(ingredientService.create(any(CreateIngredientRequest.class))).thenReturn(1);

        CreateIngredientRequest request = IngredientTestData.createIngredientRequest();
        CreateEntityResponse response = ingredientController.createIngredient(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void getIngredientTest(){

        IngredientEntity ingredient = IngredientTestData.createIngredient();
        ingredient.setId(7);

        when(ingredientService.findById(anyInt())).thenReturn(ingredient);

        IngredientResponse response = ingredientController.getIngredient(7);

        assertThat(response.getId()).isSameAs(7);
    }

    @Test
    public void listIngredientTest(){

        List<IngredientEntity> storedList = IngredientTestData.createIngredientList(true);

        when(ingredientService.list(anyInt(), anyInt())).thenReturn(storedList);

        List<IngredientResponse> ingredientList = ingredientController.getIngredientList(anyInt(), anyInt());

        assertThat(storedList.size()).isSameAs(ingredientList.size());
        assertThat(storedList.get(0).getId()).isSameAs(ingredientList.get(0).getId());
        assertThat(storedList.get(1).getId()).isSameAs(ingredientList.get(1).getId());
    }

    @Test
    public void test_deleteIngredient_successfully() {

        doNothing().when(ingredientService).delete(anyInt());

        ingredientController.deleteIngredient(5);
    }
}