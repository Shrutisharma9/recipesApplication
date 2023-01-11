package com.example.recipesApp.search.filter;

import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.example.recipesApp.config.attribute.DatabaseAttributes.INGREDIENT_KEY;

public class SearchFilterDoesNotContain implements SearchFilter {

    @Override
    public boolean couldBeApplied(SearchOperation opt) {
        return opt == SearchOperation.DOES_NOT_CONTAIN;
    }

    @Override
    public Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<RecipesEntity> root, Join<Object, Object> subRoot) {
        if (filterKey.equals(INGREDIENT_KEY))
            return cb.notLike(cb.lower(subRoot.get(filterKey).as(String.class)), "%" + filterValue + "%");

        return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");
    }
}
