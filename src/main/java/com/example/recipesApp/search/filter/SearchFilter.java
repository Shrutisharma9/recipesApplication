package com.example.recipesApp.search.filter;

import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchFilter  {
    boolean couldBeApplied(SearchOperation opt);
    Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<RecipesEntity> root,
                    Join<Object, Object> subRoot);
}