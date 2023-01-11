package com.example.recipesApp.repository;

import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.data.repository.RecipesRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipesRepositoryTest {

    @Autowired
    private RecipesRepository recipesRepository;

    @Test
    public void saveRecipeTest(){
        RecipesEntity entity = new RecipesEntity();
        entity.setType("VEGETARIAN");
        entity.setInstructions("write some instructions");
        entity.setName("Vegetable Rice");
        RecipesEntity savedRecipe = recipesRepository.save(entity);
        assertNotNull(savedRecipe);

        assertEquals("VEGETARIAN", savedRecipe.getType());
        assertNotNull(savedRecipe.getId());
      }

    @Test
    public void retrieveRecipesTest(){
        RecipesEntity entity1 = new RecipesEntity();
        entity1.setType("OTHER");
        entity1.setName("Pizza");

        RecipesEntity entity2 = new RecipesEntity();
        entity2.setType("OTHER");
        entity2.setName("Lasagna");

        RecipesEntity firstSavedEntity = recipesRepository.save(entity1);
        RecipesEntity secondSavedEntity = recipesRepository.save(entity2);

        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(recipesRepository.findAll().isEmpty());
        assertEquals(2, recipesRepository.findAll().size());
    }
}