package com.techgo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object for adding a gadget to a comparison list.
 * Contains validation annotations for input validation.
 */
public class AddToComparisonDTO {

    @NotNull(message = "Gadget ID is required")
    @Positive(message = "Gadget ID must be positive")
    private Long gadgetId;

    // Default constructor
    public AddToComparisonDTO() {}

    // Constructor with gadgetId
    public AddToComparisonDTO(Long gadgetId) {
        this.gadgetId = gadgetId;
    }

    // Getters and Setters
    public Long getGadgetId() {
        return gadgetId;
    }

    public void setGadgetId(Long gadgetId) {
        this.gadgetId = gadgetId;
    }
}