package com.example.recipesApp.service;

import com.example.recipesApp.api.request.CreateRecipeRequest;
import com.example.recipesApp.api.request.UpdateRecipeRequest;
import com.example.recipesApp.config.MessageProvider;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.data.repository.RecipesRepository;
import com.example.recipesApp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RecipeService {

        private final RecipesRepository recipeRepository;
        private final IngredientService ingredientService;
        private final MessageProvider messageProvider;

        @Autowired
        public RecipeService(RecipesRepository recipeRepository,
                             IngredientService ingredientService,
                             MessageProvider messageProvider) {
            this.recipeRepository = recipeRepository;
            this.ingredientService = ingredientService;
            this.messageProvider = messageProvider;
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

        public List<RecipesEntity> getRecipeList(int page, int size) {
            Pageable pageRequest
                    = PageRequest.of(page, size);
            return recipeRepository.findAll(pageRequest).getContent();
        }

        public RecipesEntity getRecipeById(int id) {
            return recipeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("recipe.notFound")));
        }

        public void updateRecipe(UpdateRecipeRequest updateRecipeRequest) {
            RecipesEntity recipe = recipeRepository.findById(updateRecipeRequest.getId())
                    .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("recipe.notFound")));

            Set<IngredientEntity> ingredients = Optional.ofNullable(updateRecipeRequest.getIngredientIds())
                    .map(ingredientService::getIngredientsByIds)
                    .orElse(null);

            recipe.setName(updateRecipeRequest.getName());
            recipe.setType(updateRecipeRequest.getType());
            recipe.setNumberOfServings(updateRecipeRequest.getNumberOfServings());
            recipe.setInstructions(updateRecipeRequest.getInstructions());

            if (Optional.ofNullable(ingredients).isPresent()) recipe.setRecipeIngredients(ingredients);

            recipeRepository.save(recipe);
        }

        public void deleteRecipe(int id) {
            if (!recipeRepository.existsById(id)) {
                throw new NotFoundException(messageProvider.getMessage("recipe.notFound"));
            }

            recipeRepository.deleteById(id);
        }

    }
