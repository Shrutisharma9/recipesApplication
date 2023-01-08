package com.example.recipesApp.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateIngredientRequest {

    /*@NotBlank(message = "{ingredient.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{ingredient.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{ingredient.pattern}")
    @ApiModelProperty(notes = "The name of the ingredient", example = "Potato")
    */
    private String name;

    public CreateIngredientRequest() {
    }
}
