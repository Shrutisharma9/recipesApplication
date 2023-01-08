package com.example.recipesApp.exception;

import org.springframework.http.HttpStatus;

public interface InCustomException {
    HttpStatus getStatus();
}