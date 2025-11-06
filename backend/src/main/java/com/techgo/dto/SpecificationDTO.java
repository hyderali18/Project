package com.techgo.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for GadgetSpecification entity.
 * Used for API responses for gadget specifications.
 */
public class SpecificationDTO {
    private Long id;
    private String specName;
    private String specValue;
    private LocalDateTime createdAt;

    // Default constructor
    public SpecificationDTO() {}

    // Constructor with fields
    public SpecificationDTO(String specName, String specValue) {
        this.specName = specName;
        this.specValue = specValue;
    }

    // Constructor with all fields
    public SpecificationDTO(Long id, String specName, String specValue, LocalDateTime createdAt) {
        this.id = id;
        this.specName = specName;
        this.specValue = specValue;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Utility methods
    public String getFormattedSpecName() {
        if (specName == null) return "";
        return specName.replaceAll("_", " ")
                      .replaceAll("\\b\\w", b -> b.toUpperCase());
    }

    public String getDisplayValue() {
        return specValue != null ? specValue : "N/A";
    }
}