package com.example.recipesApp.api.response;

import com.example.recipesApp.data.model.IngredientEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientResponse {
    @ApiModelProperty(notes = "The id of the returned ingredient", example = "1")
    private int id;
    @ApiModelProperty(notes = "The name of the returned recipe", example = "potato")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientResponse that = (IngredientResponse) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt);
    }
}