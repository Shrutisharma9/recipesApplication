package com.example.recipesApp.service;

import com.example.recipesApp.api.request.CreateIngredientRequest;
import com.example.recipesApp.config.MessageProvider;
import com.example.recipesApp.data.model.IngredientEntity;
import com.example.recipesApp.data.repository.IngredientRepository;
import com.example.recipesApp.exception.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    private final MessageProvider messageProvider;

    public IngredientService(IngredientRepository ingredientRepository, MessageProvider messageProvider) {
        this.ingredientRepository = ingredientRepository;
        this.messageProvider = messageProvider;
    }

    /**
     * to create new ingredient
     * @param request
     * @return
     */
    public Integer create(CreateIngredientRequest request) {
        IngredientEntity ingredient = new IngredientEntity();

        ingredient.setIngredient(request.getName());

        IngredientEntity createdIngredient = ingredientRepository.save(ingredient);
        return createdIngredient.getId();
    }

    /**
     * retrieve ingredients list based on its ids within recipe
     * @param ingredientIds
     * @return
     */
    public Set<IngredientEntity> getIngredientsByIds(List<Integer> ingredientIds) {
        return ingredientIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

    /**
     * retrieve ingredient based on its id
     * @param id
     * @return
     */
    public IngredientEntity findById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("ingredient.notFound")));
    }

    /**
     * retrieve list of all the ingredient available
     * @param page
     * @param size
     * @return
     */
    public List<IngredientEntity> list(int page, int size) {
        Pageable pageRequest
                = PageRequest.of(page, size);
        return ingredientRepository.findAll(pageRequest).getContent();
    }

    public void delete(int id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NotFoundException(messageProvider.getMessage("ingredient.notFound"));
        }
        ingredientRepository.deleteById(id);
    }
}
