# Recipe API Application

## Overview
The Recipe API Application provides endpoints to manage recipes, search for them using fuzzy logic, and retrieve them from an external API. It uses Spring Boot for the backend, integrates with an external recipe service, and supports fault tolerance with Resilience4j for retries and circuit breakers.

## Features
- Fetch recipes from a third-party API.
- Store recipes in a database.
- Search for recipes by name or cuisine using fuzzy logic.
- Fault-tolerant API calls with retries and circuit breakers.
- Swagger UI for API documentation.

## Prerequisites

Before building and running the application, ensure you have the following:

- Java 17 or higher
- Maven 3.6+
- H2 (or your preferred database)

## Installation and Setup

### 1. Clone the Repository
```bash
$ git clone https://github.com/your-repository-url/recipe-api
$ cd recipe-api
```


### 2. Build the Application
Use Maven to build the application:
```bash
$ mvn clean install
```

### 3. Run the Application
Run the application using the following command:
```bash
$ mvn spring-boot:run
```

### 5. Access the Application
- **API Endpoints**: `http://localhost:8080/api/recipes`
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

## API Endpoints

### 1. Fetch Recipes from External API
**Endpoint**: `POST /api/recipes/load`
- Fetches recipes from the third-party API and stores them in the database.

### 2. Get Recipe by ID
**Endpoint**: `GET /api/recipes/{id}`
- Fetches a recipe by its unique ID.

### 3. Search Recipes by Fuzzy Name
**Endpoint**: `GET /api/recipes/search/fuzzy?name={query}`
- Performs a fuzzy search on recipe names or cuisines.

## Fault Tolerance
- **Retry**: Automatically retries API calls up to 3 times in case of failure.
- **Circuit Breaker**: Opens the circuit when 50% of API calls fail, preventing further calls for 60 seconds.

## Testing
Run unit tests using:
```bash
$ mvn test
```

## Technologies Used
- **Spring Boot**: Backend framework.
- **Hibernate Search**: Fuzzy search.
- **PostgreSQL**: Database.
- **Resilience4j**: Fault tolerance (retries, circuit breakers).
- **Swagger**: API documentation.
- **Maven**: Build tool.
