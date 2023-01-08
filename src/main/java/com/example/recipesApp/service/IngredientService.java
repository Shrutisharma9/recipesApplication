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


    public Integer create(CreateIngredientRequest request) {
        IngredientEntity ingredient = new IngredientEntity();

        ingredient.setIngredient(request.getName());

        IngredientEntity createdIngredient = ingredientRepository.save(ingredient);
        return createdIngredient.getId();
    }


    public Set<IngredientEntity> getIngredientsByIds(List<Integer> authorIds) {
        return authorIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }
    public IngredientEntity findById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("ingredient.notFound")));
    }

    public List<IngredientEntity> list() {
        return ingredientRepository.findAll();
    }
}