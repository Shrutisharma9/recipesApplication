package com.example.recipesApp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IngredientRepositoryTest {
}
/*
@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void test_whenTryToSaveIngredientSuccess() {
        Ingredient entity = new Ingredient();
        entity.setIngredient("Tomato");
        Ingredient savedIngredient = ingredientRepository.save(entity);
        assertNotNull(savedIngredient);

        assertEquals("Tomato", savedIngredient.getIngredient());
        assertNotNull(savedIngredient.getId());
    }

    @Test
    public void test_whenTryGetTokenListSuccess() {
        Ingredient entity1 = new Ingredient();
        entity1.setIngredient("Tomato");

        Ingredient entity2 = new Ingredient();
        entity2.setIngredient("Potato");

        Ingredient firstSavedEntity = ingredientRepository.save(entity1);
        Ingredient secondSavedEntity = ingredientRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(ingredientRepository.findAll().isEmpty());
        assertEquals(2, ingredientRepository.findAll().size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void test_whenTryAddSameIngredientTwiceTokenListFails() {
        Ingredient entity1 = new Ingredient();
        entity1.setIngredient("Tomato");

        Ingredient entity2 = new Ingredient();
        entity2.setIngredient("Tomato");

        ingredientRepository.save(entity1);
        ingredientRepository.save(entity2);

    }
}*/