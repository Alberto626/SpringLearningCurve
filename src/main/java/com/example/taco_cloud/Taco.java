package com.example.taco_cloud;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull //name cannot be null but can be empty
    @Size(min = 5, message = "Name must be at least 5 characters long") //string must be a character length of 5 minimum
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least 1 Ingredient")
    private List<Ingredient> ingredients;

}
