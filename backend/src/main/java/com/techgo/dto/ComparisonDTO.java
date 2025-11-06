package com.techgo.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for ComparisonList entity.
 * Used for API responses for comparison lists.
 */
public class ComparisonDTO {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean expired;
    private List<ComparisonItemDTO> items;
    private int gadgetCount;

    // Default constructor
    public ComparisonDTO() {}

    // Constructor with basic fields
    public ComparisonDTO(String id, String name, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.expired = LocalDateTime.now().isAfter(expiresAt);
        this.gadgetCount = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public List<ComparisonItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ComparisonItemDTO> items) {
        this.items = items;
        this.gadgetCount = items != null ? items.size() : 0;
    }

    public int getGadgetCount() {
        return gadgetCount;
    }

    public void setGadgetCount(int gadgetCount) {
        this.gadgetCount = gadgetCount;
    }

    // Utility methods
    public boolean canAddMoreGadgets() {
        return gadgetCount < 3 && !expired;
    }

    public int getRemainingSlots() {
        return expired ? 0 : Math.max(0, 3 - gadgetCount);
    }

    public String getFormattedCreatedAt() {
        return createdAt != null ? createdAt.toLocalDate().toString() : null;
    }

    public String getFormattedExpiresAt() {
        return expiresAt != null ? expiresAt.toLocalDate().toString() : null;
    }

    public long getDaysUntilExpiry() {
        if (expiresAt == null) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDateTime.now(), expiresAt);
    }

    public String getExpiryStatus() {
        if (expired) return "Expired";
        long days = getDaysUntilExpiry();
        if (days == 0) return "Expires today";
        if (days == 1) return "Expires tomorrow";
        return "Expires in " + days + " days";
    }
}