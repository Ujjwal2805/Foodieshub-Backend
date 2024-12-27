package com.example.assignment.services;

import com.example.assignment.config.RecipeConfig;
import com.example.assignment.entities.Recipe;
import com.example.assignment.entities.RecipeResponse;
import com.example.assignment.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    private final RestTemplate restTemplate;

    private final String recipeApiUrl;

    public RecipeService(RestTemplate restTemplate, String recipeApiUrl) {
        this.restTemplate = restTemplate;
        this.recipeApiUrl = recipeApiUrl;
    }


    public void loadRecipes() {
        try {
            logger.info("Fetching recipes from external API: {}", recipeApiUrl);
            RecipeResponse recipeResponse = restTemplate.getForObject(recipeApiUrl, RecipeResponse.class);

            if (recipeResponse == null || recipeResponse.getRecipes() == null || recipeResponse.getRecipes().isEmpty()) {
                logger.warn("No recipes found in the API response.");
                return;
            }

            recipeRepository.saveAll(recipeResponse.getRecipes());
            logger.info("Successfully saved {} recipes to the database.", recipeResponse.getRecipes().size());
        } catch (Exception e) {
            logger.error("Error occurred while fetching or saving recipes: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load recipes from external API.", e);
        }
    }

    public Recipe findRecipeById(Long id) {
        logger.info("Finding recipe with ID: {}", id);
        return recipeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Recipe not found with ID: {}", id);
                    return new RuntimeException("Recipe not found with ID: " + id);
                });
    }

    public List<Recipe> searchByNameFuzzy(String name) {
        logger.info("Performing fuzzy search for recipes with name or cuisine matching: {}", name);

        try {
            SearchSession searchSession = Search.session(entityManager);
            SearchResult<Recipe> result = searchSession.search(Recipe.class)
                    .where(f -> f.match()
                            .fields("name", "cuisine")
                            .matching(name)
                            .fuzzy(2))
                    .fetch(10);

            logger.info("Fuzzy search returned {} results.", result.hits().size());
            return result.hits();
        } catch (Exception e) {
            logger.error("Error occurred during fuzzy search: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to perform fuzzy search for recipes.", e);
        }
    }
}
