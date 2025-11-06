package com.techgo.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing a gadget in the TechGo comparison system.
 * Gadgets are electronic devices that users can compare side-by-side.
 */
@Entity
@Table(name = "gadgets", indexes = {
    @Index(name = "idx_brand", columnList = "brand"),
    @Index(name = "idx_category", columnList = "category"),
    @Index(name = "idx_price", columnList = "price"),
    @Index(name = "idx_rating", columnList = "rating")
})
@EntityListeners(AuditingEntityListener.class)
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 100)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('mobiles', 'laptops', 'tablets', 'earphones', 'speakers')")
    private Category category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column
    private Integer reviewCount = 0;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "gadget", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GadgetSpecification> specifications = new ArrayList<>();

    @OneToMany(mappedBy = "gadget", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    // Default constructor
    public Gadget() {}

    // Constructor with required fields
    public Gadget(String name, String brand, Category category, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

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

    public List<GadgetSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GadgetSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // Utility methods
    public void addSpecification(GadgetSpecification specification) {
        specifications.add(specification);
        specification.setGadget(this);
    }

    public void removeSpecification(GadgetSpecification specification) {
        specifications.remove(specification);
        specification.setGadget(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setGadget(this);
        updateRating();
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setGadget(null);
        updateRating();
    }

    /**
     * Updates the average rating based on all reviews
     */
    public void updateRating() {
        if (reviews == null || reviews.isEmpty()) {
            this.rating = BigDecimal.ZERO;
            this.reviewCount = 0;
            return;
        }

        BigDecimal totalRating = reviews.stream()
                .map(Review::getRating)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.rating = totalRating.divide(BigDecimal.valueOf(reviews.size()), 2, BigDecimal.ROUND_HALF_UP);
        this.reviewCount = reviews.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gadget gadget = (Gadget) o;
        return Objects.equals(id, gadget.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Gadget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}