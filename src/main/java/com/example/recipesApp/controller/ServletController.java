package com.example.recipesApp.controller;


import com.example.recipesApp.data.model.RecipesEntity;
import com.example.recipesApp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class ServletController {

    private static final String HOME_PAGE = "home";

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(name = "x", required = false) final String appliedOwningEntityFilter) {

        return HOME_PAGE;
    }

    @GetMapping("/recipes")
    public String getAllRecipes(Model model) {
        final List<RecipesEntity> recipesEntities = recipeService.getRecipeList();
        model.addAttribute("recipes", recipesEntities);
        return HOME_PAGE;
    }
/*
    @GetMapping("/search")
    public String searchRecipes(Model model, @RequestParam(name = "keyword", required = true) final String keyword) {

        if(StringUtils.isNotEmpty(keyword) && keyword.length() > 1){
            final List<RecipesEntity> recipesEntities = recipeService.findRecipe(keyword);
            if(!CollectionUtils.isEmpty(recipesEntities)){
                model.addAttribute("recipeFuzzy", recipesEntities);
            }
            else {
                model.addAttribute("info_s", "No Record found");
            }
        }
        else {
            model.addAttribute("error_s", "keyword is empty or less than 2 alphabets");
        }
        return HOME_PAGE;
    }*/

}