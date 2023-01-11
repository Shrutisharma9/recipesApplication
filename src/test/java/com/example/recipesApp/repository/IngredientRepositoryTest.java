package com.example.recipesApp.repository;

import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.repository.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void saveIngredientTest(){
        IngredientEntity entity = new IngredientEntity();
        entity.setIngredient("Potato");
        IngredientEntity savedIngredient = ingredientRepository.save(entity);
        assertNotNull(savedIngredient);

        assertEquals("Potato", savedIngredient.getIngredient());
        assertNotNull(savedIngredient.getId());
    }

    @Test
    public void retrieveIngredientList(){
        IngredientEntity entity1 = new IngredientEntity();
        entity1.setIngredient("Potato");

        IngredientEntity entity2 = new IngredientEntity();
        entity2.setIngredient("Tomato");

        IngredientEntity firstSavedEntity = ingredientRepository.save(entity1);
        IngredientEntity secondSavedEntity = ingredientRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(ingredientRepository.findAll().isEmpty());
        assertEquals(2, ingredientRepository.findAll().size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveDuplicateIngredientTest(){
        IngredientEntity entity1 = new IngredientEntity();
        entity1.setIngredient("Potato");

        IngredientEntity entity2 = new IngredientEntity();
        entity2.setIngredient("Potato");

        ingredientRepository.save(entity1);
        ingredientRepository.save(entity2);
    }

}