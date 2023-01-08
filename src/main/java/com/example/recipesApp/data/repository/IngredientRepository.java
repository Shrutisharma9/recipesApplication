package com.example.recipesApp.data.repository;

import com.example.recipesApp.data.model.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {
}
