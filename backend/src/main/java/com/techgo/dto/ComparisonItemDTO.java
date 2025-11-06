package com.techgo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for ComparisonItem entity.
 * Used for API responses for items in comparison lists.
 */
public class ComparisonItemDTO {
    private Long id;
    private Long gadgetId;
    private String name;
    private String brand;
    private String category;
    private BigDecimal price;
    private String imageUrl;
    private BigDecimal rating;
    private Integer reviewCount;
    private LocalDateTime addedAt;

    // Default constructor
    public ComparisonItemDTO() {}

    // Constructor with basic fields
    public ComparisonItemDTO(Long id, Long gadgetId, String name, String brand,
                           String category, BigDecimal price, String imageUrl,
                           BigDecimal rating, Integer reviewCount, LocalDateTime addedAt) {
        this.id = id;
        this.gadgetId = gadgetId;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.addedAt = addedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGadgetId() {
        return gadgetId;
    }

    public void setGadgetId(Long gadgetId) {
        this.gadgetId = gadgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    // Utility methods
    public String getFormattedPrice() {
        return price != null ? String.format("$%.2f", price) : null;
    }

    public String getFormattedRating() {
        return rating != null ? String.format("%.1f", rating) : "0.0";
    }

    public String getFormattedAddedAt() {
        return addedAt != null ? addedAt.toLocalDate().toString() : null;
    }

    public String getFullTitle() {
        return brand + " " + name;
    }
}