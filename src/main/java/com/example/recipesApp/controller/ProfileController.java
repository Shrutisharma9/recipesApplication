package com.example.recipesApp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/v1")
@RestController
@ApiIgnore
public class ProfileController {

    @Value("${application.environment}")
    private String applicationEnv;

    @GetMapping
    public String getapplicationEnv(){
        return applicationEnv;
    }

}