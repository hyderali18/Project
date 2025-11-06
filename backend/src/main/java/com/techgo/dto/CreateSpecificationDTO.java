package com.techgo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for creating a new GadgetSpecification.
 * Contains validation annotations for input validation.
 */
public class CreateSpecificationDTO {

    @NotBlank(message = "Specification name is required")
    @Size(max = 100, message = "Specification name must not exceed 100 characters")
    private String specName;

    @NotBlank(message = "Specification value is required")
    private String specValue;

    // Default constructor
    public CreateSpecificationDTO() {}

    // Constructor with fields
    public CreateSpecificationDTO(String specName, String specValue) {
        this.specName = specName;
        this.specValue = specValue;
    }

    // Getters and Setters
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
}