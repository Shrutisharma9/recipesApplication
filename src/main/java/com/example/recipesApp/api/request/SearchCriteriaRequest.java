package com.example.recipesApp.api.request;

import com.example.recipesApp.api.request.input.FilterKeyReqInput;
import com.example.recipesApp.api.request.input.SearchOperationReqInput;
import com.example.recipesApp.validator.EnumValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Valid
@Getter
@Setter
public class SearchCriteriaRequest {

    @ApiModelProperty(notes = "The name of the column you want to search on available fields are " +
              "name, " +
              "numberOfServings, " +
              "type, " +
              "instructions, " +
              "ingredientName)", example = "name")
    @EnumValidator(enumClass = FilterKeyReqInput.class, message = "{filterKey.invalid}")
    private String filterKey;


    @ApiModelProperty(notes = "The actual phrase you want to do search on", example = "Pasta")
    private Object value;

    @ApiModelProperty(notes = "The operation type you wanted to search (cn - contains, " +
                "nc - doesn't contain, " +
                "eq - equals, " +
                "ne - not equals", example = "cn")
    @EnumValidator(enumClass = SearchOperationReqInput.class, message = "{searchOperation.invalid}")
    private String operation;

    @ApiModelProperty(hidden = true)
    private String dataOption;

    public SearchCriteriaRequest() {
    }

    public SearchCriteriaRequest(String filterKey, Object value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }

}
