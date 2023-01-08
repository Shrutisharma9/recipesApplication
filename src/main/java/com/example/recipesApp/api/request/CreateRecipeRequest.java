package com.example.recipesApp.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateRecipeRequest {
    @NotBlank(message = "{recipeName.notBlank}")
/*    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{recipeName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{recipeName.pattern}")
    @ApiModelProperty(notes = "The name of the recipe", example = "Pasta")*/
    private String name;

 /*   @ApiModelProperty(notes = "The type of the recipe", example = "VEGETARIAN")
    @EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}")*/
    private String type;

  /*  @NotNull(message = "{numberOfServings.notNull}")
    @Positive(message = "{numberOfServings.positive}")
    @ApiModelProperty(notes = "The number of servings per recipe", example = "4")*/
    private int numberOfServings;

  /*  @ApiModelProperty(notes = "The ids of the ingredients needed to make the recipe", example = "[1,2]")
  */
    private List<Integer> ingredientIds;

/*    @NotBlank(message = "{instructions.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT, message = "{instructions.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{instructions.pattern}")
    @ApiModelProperty(notes = "The instructions to create the recipe", example = "Chop the tomato, stir and fry, boil and serve")
  */
    private String instructions;

    public CreateRecipeRequest() {
    }

    public CreateRecipeRequest(String name, String type, int numberOfServings, List<Integer> ingredientIds, String instructions) {
        this.name = name;
        this.type = type;
        this.numberOfServings = numberOfServings;
        this.ingredientIds = ingredientIds;
        this.instructions = instructions;
    }
}