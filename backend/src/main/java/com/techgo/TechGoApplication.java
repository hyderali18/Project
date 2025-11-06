package com.techgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main application class for TechGo Backend
 *
 * TechGo is a gadget comparison web application that allows users to:
 * - Compare electronic gadgets like mobile phones, laptops, tablets, etc.
 * - Search and filter gadgets by various criteria
 * - View detailed specifications and user reviews
 * - Create side-by-side comparisons
 * - Admin panel for content management
 */
@SpringBootApplication
@EnableJpaAuditing
public class TechGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechGoApplication.class, args);
    }
}