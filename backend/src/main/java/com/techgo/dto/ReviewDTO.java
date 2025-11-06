package com.techgo.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Review entity.
 * Used for API responses for user reviews.
 */
public class ReviewDTO {
    private Long id;
    private Long gadgetId;
    private String gadget;
    private String userName;
    private String email;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    // Default constructor
    public ReviewDTO() {}

    // Constructor with fields
    public ReviewDTO(Long id, Long gadgetId, String gadget, String userName,
                     String email, Integer rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.gadgetId = gadgetId;
        this.gadget = gadget;
        this.userName = userName;
        this.email = email;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public String getGadget() {
        return gadget;
    }

    public void setGadget(String gadget) {
        this.gadget = gadget;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Utility methods
    public String getFormattedDate() {
        return createdAt != null ? createdAt.toLocalDate().toString() : null;
    }

    public String getDisplayName() {
        return userName != null ? userName.substring(0, 1).toUpperCase() +
               userName.substring(1).toLowerCase() : null;
    }

    public String getMaskedEmail() {
        if (email == null || !email.contains("@")) return email;
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];

        if (username.length() <= 2) {
            return username + "@" + domain;
        }

        String maskedUsername = username.substring(0, 2) + "***";
        return maskedUsername + "@" + domain;
    }
}