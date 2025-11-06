package com.techgo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for creating a new ComparisonList.
 * Contains validation annotations for input validation.
 */
public class CreateComparisonDTO {

    @NotBlank(message = "Comparison name is required")
    @Size(max = 255, message = "Comparison name must not exceed 255 characters")
    private String name;

    // Default constructor
    public CreateComparisonDTO() {}

    // Constructor with name
    public CreateComparisonDTO(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}