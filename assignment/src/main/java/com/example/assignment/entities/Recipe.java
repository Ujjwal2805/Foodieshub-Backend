package com.example.assignment.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.util.List;
import java.util.Objects;

/**
 * Entity representing a Recipe.
 */
@Getter
@Setter
@Entity
@Indexed
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @FullTextField
    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @ElementCollection
    @JsonProperty("ingredients")
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> ingredients;

    @ElementCollection
    @JsonProperty("instructions")
    @CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> instructions;

    @JsonProperty("prepTimeMinutes")
    @Column(nullable = false)
    private int prepTimeMinutes;

    @JsonProperty("cookTimeMinutes")
    @Column(nullable = false)
    private int cookTimeMinutes;

    @JsonProperty("servings")
    @Column(nullable = false)
    private int servings;

    @JsonProperty("difficulty")
    @Column(nullable = false)
    private String difficulty;

    @FullTextField
    @JsonProperty("cuisine")
    @Column(nullable = false)
    private String cuisine;

    @JsonProperty("caloriesPerServing")
    private int caloriesPerServing;

    @ElementCollection
    @JsonProperty("tags")
    @CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> tags;

    @JsonProperty("userId")
    @Column(nullable = false)
    private Long userId;

    @JsonProperty("image")
    private String image;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("reviewCount")
    private int reviewCount;

    @ElementCollection
    @JsonProperty("mealType")
    @CollectionTable(name = "recipe_meal_types", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> mealType;

    public Recipe() {
        // Default constructor for JPA
    }

    public Recipe(Long id, String name, List<String> ingredients, List<String> instructions, int prepTimeMinutes,
                  int cookTimeMinutes, int servings, String difficulty, String cuisine, int caloriesPerServing,
                  List<String> tags, Long userId, String image, double rating, int reviewCount, List<String> mealType) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.servings = servings;
        this.difficulty = difficulty;
        this.cuisine = cuisine;
        this.caloriesPerServing = caloriesPerServing;
        this.tags = tags;
        this.userId = userId;
        this.image = image;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", instructions=" + instructions +
                ", prepTimeMinutes=" + prepTimeMinutes +
                ", cookTimeMinutes=" + cookTimeMinutes +
                ", servings=" + servings +
                ", difficulty='" + difficulty + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", caloriesPerServing=" + caloriesPerServing +
                ", tags=" + tags +
                ", userId=" + userId +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", mealType=" + mealType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return prepTimeMinutes == recipe.prepTimeMinutes &&
                cookTimeMinutes == recipe.cookTimeMinutes &&
                servings == recipe.servings &&
                caloriesPerServing == recipe.caloriesPerServing &&
                Double.compare(recipe.rating, rating) == 0 &&
                reviewCount == recipe.reviewCount &&
                Objects.equals(id, recipe.id) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(instructions, recipe.instructions) &&
                Objects.equals(difficulty, recipe.difficulty) &&
                Objects.equals(cuisine, recipe.cuisine) &&
                Objects.equals(tags, recipe.tags) &&
                Objects.equals(userId, recipe.userId) &&
                Objects.equals(image, recipe.image) &&
                Objects.equals(mealType, recipe.mealType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients, instructions, prepTimeMinutes, cookTimeMinutes, servings, difficulty,
                cuisine, caloriesPerServing, tags, userId, image, rating, reviewCount, mealType);
    }
}
