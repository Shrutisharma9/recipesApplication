package com.example.recipesApp.service;

import com.example.recipesApp.testDataBuilder.IngredientTestData;
import com.example.recipesApp.api.request.CreateIngredientRequest;
import com.example.recipesApp.config.MessageProvider;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.repository.IngredientRepository;
import com.example.recipesApp.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private MessageProvider messageProvider;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    public void createIngredientTest(){
        CreateIngredientRequest request = IngredientTestData.createIngredientRequest();
        IngredientEntity response = IngredientTestData.createIngredient();
        response.setId(4);

        when(ingredientRepository.save(any(IngredientEntity.class))).thenReturn(response);
        Integer id = ingredientService.create(request);
        assertThat(id).isSameAs(response.getId());
    }

    @Test
    public void deleteIngredientTest(){
    when(ingredientRepository.existsById(anyInt())).thenReturn(true);
    doNothing().when(ingredientRepository).deleteById(anyInt());

    ingredientService.delete(4);
    }


    @Test(expected = NotFoundException.class)
    public void deleteNonExistingIngredientTest(){
        when(ingredientRepository.existsById(anyInt())).thenReturn(false);

        ingredientService.delete(2);
    }

}