package com.example.assignment.services;

import com.example.assignment.entities.Recipe;
import com.example.assignment.entities.RecipeResponse;
import com.example.assignment.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.mockito.Mockito.*;

class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void testLoadRecipes() {
        MockitoAnnotations.openMocks(this);

        RecipeResponse recipeResponse = new RecipeResponse();
        recipeResponse.setRecipes(Collections.singletonList(new Recipe()));
        ResponseEntity<RecipeResponse> response = ResponseEntity.ok(recipeResponse);

        when(restTemplate.getForEntity(anyString(), eq(RecipeResponse.class))).thenReturn(response);

        recipeService.loadRecipes();

        verify(recipeRepository, times(1)).saveAll(recipeResponse.getRecipes());
    }
}

