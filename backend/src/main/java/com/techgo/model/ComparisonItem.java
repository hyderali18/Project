package com.techgo.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing a gadget in a comparison list.
 * This is a join table entity linking ComparisonList and Gadget.
 */
@Entity
@Table(name = "comparison_items",
       uniqueConstraints = @UniqueConstraint(columnNames = {"comparison_id", "gadget_id"}))
@EntityListeners(AuditingEntityListener.class)
public class ComparisonItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comparison_id", nullable = false)
    private ComparisonList comparisonList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gadget_id", nullable = false)
    private Gadget gadget;

    @CreatedDate
    @Column(name = "added_at", nullable = false, updatable = false)
    private LocalDateTime addedAt;

    // Default constructor
    public ComparisonItem() {}

    // Constructor with required fields
    public ComparisonItem(ComparisonList comparisonList, Gadget gadget) {
        this.comparisonList = comparisonList;
        this.gadget = gadget;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparisonList getComparisonList() {
        return comparisonList;
    }

    public void setComparisonList(ComparisonList comparisonList) {
        this.comparisonList = comparisonList;
    }

    public Gadget getGadget() {
        return gadget;
    }

    public void setGadget(Gadget gadget) {
        this.gadget = gadget;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonItem that = (ComparisonItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ComparisonItem{" +
                "id=" + id +
                ", gadget=" + (gadget != null ? gadget.getName() : "null") +
                ", addedAt=" + addedAt +
                '}';
    }
}