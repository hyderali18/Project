package com.techgo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity representing a comparison list containing multiple gadgets.
 * Users can create comparison lists to compare 2-3 gadgets side-by-side.
 */
@Entity
@Table(name = "comparison_lists")
@EntityListeners(AuditingEntityListener.class)
public class ComparisonList {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "Comparison name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @OneToMany(mappedBy = "comparisonList", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ComparisonItem> items = new ArrayList<>();

    // Default constructor
    public ComparisonList() {
        this.id = UUID.randomUUID().toString();
        this.expiresAt = LocalDateTime.now().plusDays(7);
    }

    // Constructor with name
    public ComparisonList(String name) {
        this();
        this.name = name;
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

    public List<ComparisonItem> getItems() {
        return items;
    }

    public void setItems(List<ComparisonItem> items) {
        this.items = items;
    }

    // Utility methods
    public void addItem(ComparisonItem item) {
        items.add(item);
        item.setComparisonList(this);
    }

    public void removeItem(ComparisonItem item) {
        items.remove(item);
        item.setComparisonList(null);
    }

    /**
     * Add a gadget to this comparison list
     * @param gadget the gadget to add
     * @return the created ComparisonItem
     */
    public ComparisonItem addGadget(Gadget gadget) {
        // Check if gadget already exists in comparison
        for (ComparisonItem item : items) {
            if (item.getGadget().equals(gadget)) {
                return item; // Return existing item
            }
        }

        // Check maximum limit (3 gadgets)
        if (items.size() >= 3) {
            throw new IllegalStateException("Cannot add more than 3 gadgets to a comparison");
        }

        ComparisonItem item = new ComparisonItem(this, gadget);
        items.add(item);
        return item;
    }

    /**
     * Remove a gadget from this comparison list
     * @param gadget the gadget to remove
     * @return true if gadget was removed, false if not found
     */
    public boolean removeGadget(Gadget gadget) {
        return items.removeIf(item -> item.getGadget().equals(gadget));
    }

    /**
     * Check if this comparison list has expired
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    /**
     * Get the number of gadgets in this comparison
     * @return the count of gadgets
     */
    public int getGadgetCount() {
        return items.size();
    }

    /**
     * Check if a gadget is in this comparison
     * @param gadget the gadget to check
     * @return true if gadget is in comparison, false otherwise
     */
    public boolean containsGadget(Gadget gadget) {
        return items.stream().anyMatch(item -> item.getGadget().equals(gadget));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonList that = (ComparisonList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ComparisonList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gadgetCount=" + getGadgetCount() +
                ", createdAt=" + createdAt +
                '}';
    }
}