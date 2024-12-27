package com.example.assignment.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RecipeConfig {

    @Value("${recipe.api.url}")
    private String recipeApiUrl;

    /**
     * Provides a RestTemplate bean for making HTTP calls.
     *
     * @return an instance of RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Provides the URL for the recipe API.
     *
     * @return the recipe API URL.
     */
    @Bean
    public String recipeApiUrl() {
        return recipeApiUrl;
    }
}
