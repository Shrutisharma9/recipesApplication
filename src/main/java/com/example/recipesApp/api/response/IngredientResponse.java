package com.example.recipesApp.api.response;

import com.example.recipesApp.data.model.IngredientEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientResponse {
    private int id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    public IngredientResponse() {
    }

    public IngredientResponse(IngredientEntity ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getIngredient();
        this.createdAt = ingredient.getCreatedAt();
        this.updatedAt = ingredient.getUpdatedAt();
    }

}