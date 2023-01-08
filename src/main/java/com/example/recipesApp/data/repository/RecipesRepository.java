package com.example.recipesApp.data.repository;

import com.example.recipesApp.data.model.RecipesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends JpaRepository<RecipesEntity, Integer>, JpaSpecificationExecutor<RecipesEntity> {
}
