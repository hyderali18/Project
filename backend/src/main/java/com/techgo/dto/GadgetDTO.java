package com.techgo.dto;

import com.techgo.model.Category;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Gadget entity.
 * Used for API responses to avoid exposing internal entity structure.
 */
public class GadgetDTO {
    private Long id;
    private String name;
    private String brand;
    private Category category;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private BigDecimal rating;
    private Integer reviewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SpecificationDTO> specifications;

    // Default constructor
    public GadgetDTO() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SpecificationDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationDTO> specifications) {
        this.specifications = specifications;
    }

    // Utility methods
    public String getCategoryDisplayName() {
        return category != null ? category.getDisplayName() : null;
    }

    public String getFormattedPrice() {
        return price != null ? String.format("$%.2f", price) : null;
    }

    public String getFormattedRating() {
        return rating != null ? String.format("%.1f", rating) : "0.0";
    }
}