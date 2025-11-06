package com.techgo.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing a specification for a gadget.
 * Specifications are key-value pairs that describe the technical details of a gadget.
 */
@Entity
@Table(name = "gadget_specifications", indexes = {
    @Index(name = "idx_gadget_id", columnList = "gadget_id")
})
@EntityListeners(AuditingEntityListener.class)
public class GadgetSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gadget_id", nullable = false)
    private Gadget gadget;

    @Column(name = "spec_name", nullable = false, length = 100)
    private String specName;

    @Column(name = "spec_value", nullable = false, columnDefinition = "TEXT")
    private String specValue;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default constructor
    public GadgetSpecification() {}

    // Constructor with required fields
    public GadgetSpecification(Gadget gadget, String specName, String specValue) {
        this.gadget = gadget;
        this.specName = specName;
        this.specValue = specValue;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gadget getGadget() {
        return gadget;
    }

    public void setGadget(Gadget gadget) {
        this.gadget = gadget;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GadgetSpecification that = (GadgetSpecification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GadgetSpecification{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", specValue='" + specValue + '\'' +
                '}';
    }
}