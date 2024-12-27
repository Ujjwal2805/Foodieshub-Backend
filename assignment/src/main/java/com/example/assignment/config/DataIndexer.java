package com.example.assignment.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataIndexer {

    private static final Logger logger = LoggerFactory.getLogger(DataIndexer.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Triggers the mass indexing of entities for Hibernate Search.
     */
    @Transactional
    public void indexData() {
        try {
            logger.info("Starting the mass indexing process...");
            SearchSession searchSession = Search.session(entityManager);
            searchSession.massIndexer()
                    .startAndWait();
            logger.info("Mass indexing completed successfully.");
        } catch (InterruptedException e) {
            logger.error("Mass indexing was interrupted: {}", e.getMessage(), e);
            Thread.currentThread().interrupt(); // Restore the interrupt status
            throw new RuntimeException("Mass indexing was interrupted", e);
        } catch (Exception e) {
            logger.error("An error occurred during mass indexing: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to perform mass indexing", e);
        }
    }
}
