package com.example.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Represents a response containing a list of recipes.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    /**
     * List of Recipe objects in the response.
     */
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return this.recipes;
    }

    public void setRecipes(List<Recipe> recipe) {
        this.recipes = recipe;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipes=" + recipes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeResponse that = (RecipeResponse) o;
        return Objects.equals(recipes, that.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipes);
    }
}
