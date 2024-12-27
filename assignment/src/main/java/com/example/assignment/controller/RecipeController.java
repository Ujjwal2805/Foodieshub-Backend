package com.example.assignment.controller;

import com.example.assignment.entities.Recipe;
import com.example.assignment.services.RecipeService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;


    /**
     * Initializes the recipe data by loading it from the external API.
     */
    @PostConstruct
    public void init() {
        try {
            logger.info("Initializing recipes from external API.");
            recipeService.loadRecipes();
            logger.info("Recipe initialization completed successfully.");
        } catch (Exception e) {
            logger.error("Error during recipe initialization: {}", e.getMessage(), e);
        }
    }

    /**
     * Fetches a recipe by its ID.
     *
     * @param id The ID of the recipe.
     * @return ResponseEntity containing the recipe or an error response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        try {
            logger.info("Fetching recipe with ID: {}", id);
            Recipe recipe = recipeService.findRecipeById(id);
            return ResponseEntity.ok(recipe);
        } catch (RuntimeException e) {
            logger.error("Error fetching recipe with ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Performs a fuzzy search for recipes by name.
     *
     * @param name The search query for recipe names.
     * @return A list of recipes matching the fuzzy search criteria.
     */
    @GetMapping("/search/fuzzy")
    public ResponseEntity<List<Recipe>> searchByFuzzy(@RequestParam String name) {
        try {
            logger.info("Performing fuzzy search for recipes with name: {}", name);
            List<Recipe> recipes = recipeService.searchByNameFuzzy(name);
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            logger.error("Error during fuzzy search for name '{}': {}", name, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
