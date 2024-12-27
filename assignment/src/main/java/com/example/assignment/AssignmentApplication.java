package com.example.assignment;

import com.example.assignment.config.DataIndexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

    private final DataIndexer dataIndexer;
    private static final Logger logger = LoggerFactory.getLogger(AssignmentApplication.class);


    public AssignmentApplication(DataIndexer dataIndexer) {
        this.dataIndexer = dataIndexer;
    }

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            logger.info("Starting data indexing...");
            dataIndexer.indexData();
            logger.info("Data indexing completed.");
        } catch (Exception e) {
            logger.error("Error occurred while indexing data: {}", e.getMessage(), e);
        }
    }


}
