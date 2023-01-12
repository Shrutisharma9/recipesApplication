package com.example.recipesApp.service;

import com.example.recipesApp.api.request.CreateRecipeRequest;
import com.example.recipesApp.api.request.RecipeSearchRequest;
import com.example.recipesApp.api.request.SearchCriteriaRequest;
import com.example.recipesApp.api.request.UpdateRecipeRequest;
import com.example.recipesApp.api.response.RecipeResponse;
import com.example.recipesApp.config.MessageProvider;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.data.repository.RecipesRepository;
import com.example.recipesApp.exception.NotFoundException;
import com.example.recipesApp.search.RecipeSpecificationBuilder;
import com.example.recipesApp.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * the method is to create new recipe
     * @param createRecipeRequest
     * @return
     */
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

        /**
        *  this is the simple method to retrieve all the recipes available in the table and show the list on the home page
        * @return
        */
        public List<RecipesEntity> getRecipeList(){
            return recipeRepository.findAll();
        }

    /**
     * it returns the recipe list based on page number and number of records passed as input
     * for ex. page: 0 and size: 5 then it will show page 0 and 5 records on that page
     * @param page
     * @param size
     * @return
     */
        public List<RecipesEntity> getRecipeList(int page, int size) {
            Pageable pageRequest
                    = PageRequest.of(page, size);
            return recipeRepository.findAll(pageRequest).getContent();
        }

        public RecipesEntity getRecipeById(int id) {
            return recipeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("recipe.notFound")));
        }

    /**
     * this is to update existing recipe
     * @param updateRecipeRequest
     */
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

    /**
     * this will return the list of all the recipes available in the database based on input data either partial or full details
     * @param recipeSearchRequest
     * @param page
     * @param size
     * @return
     */
    public List<RecipeResponse> findBySearchCriteria(RecipeSearchRequest recipeSearchRequest, int page, int size) {
        List<SearchCriteria> searchCriterionRequests = new ArrayList<>();
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(searchCriterionRequests);
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("name")
                .ascending());

        Specification<RecipesEntity> recipeSpecification = createRecipeSpecification(recipeSearchRequest, builder);
        Page<RecipesEntity> filteredRecipes = recipeRepository.findAll(recipeSpecification, pageRequest);

        return filteredRecipes.toList().stream()
                .map(RecipeResponse::new)
                .collect(Collectors.toList());
    }

    private Specification<RecipesEntity> createRecipeSpecification(RecipeSearchRequest recipeSearchRequest,
                                                                   RecipeSpecificationBuilder builder) {
        List<SearchCriteriaRequest> searchCriteriaRequests = recipeSearchRequest.getSearchCriteriaRequests();

        if (Optional.ofNullable(searchCriteriaRequests).isPresent()) {
            List<SearchCriteria> searchCriteriaList = searchCriteriaRequests.stream()
                    .map(SearchCriteria::new)
                    .collect(Collectors.toList());

            if (!searchCriteriaList.isEmpty()) searchCriteriaList.forEach(criteria -> {
                criteria.setDataOption(recipeSearchRequest.getDataOption());
                builder.with(criteria);
            });
        }

        return builder
                .build()
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("criteria.notFound")));
    }

    }
