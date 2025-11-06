package com.techgo.controller;

import com.techgo.dto.CreateGadgetDTO;
import com.techgo.dto.GadgetDTO;
import com.techgo.dto.SpecificationDTO;
import com.techgo.service.GadgetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST Controller for gadget management operations.
 * Provides endpoints for CRUD operations and search functionality.
 */
@RestController
@RequestMapping("/gadgets")
@CrossOrigin(origins = "http://localhost:3000")
public class GadgetController {

    private final GadgetService gadgetService;

    public GadgetController(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
    }

    /**
     * Get all gadgets with pagination and filtering
     * GET /api/gadgets?page=0&size=20&category=mobiles&brand=apple&minPrice=500&maxPrice=1500&minRating=4&search=iphone
     */
    @GetMapping
    public ResponseEntity<?> getAllGadgets(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        var gadgets = gadgetService.getAllGadgets(
            category, brand, minPrice, maxPrice, minRating, search, page, size, sortBy);

        return ResponseEntity.ok(gadgets);
    }

    /**
     * Get gadget by ID
     * GET /api/gadgets/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GadgetDTO> getGadgetById(@PathVariable Long id) {
        GadgetDTO gadget = gadgetService.getGadgetById(id);
        return ResponseEntity.ok(gadget);
    }

    /**
     * Search gadgets by name
     * GET /api/gadgets/search?q=iphone&category=mobiles&brand=apple&page=0&size=10
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchGadgets(
            @RequestParam("q") String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var gadgets = gadgetService.searchGadgets(query, category, brand, page, size);
        return ResponseEntity.ok(gadgets);
    }

    /**
     * Get featured gadgets
     * GET /api/gadgets/featured?category=mobiles&limit=6
     */
    @GetMapping("/featured")
    public ResponseEntity<List<GadgetDTO>> getFeaturedGadgets(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "6") int limit) {

        List<GadgetDTO> gadgets = gadgetService.getFeaturedGadgets(category, limit);
        return ResponseEntity.ok(gadgets);
    }

    /**
     * Create a new gadget (Admin only)
     * POST /api/gadgets
     */
    @PostMapping
    public ResponseEntity<GadgetDTO> createGadget(@Valid @RequestBody CreateGadgetDTO createGadgetDTO) {
        GadgetDTO createdGadget = gadgetService.createGadget(createGadgetDTO);
        return new ResponseEntity<>(createdGadget, HttpStatus.CREATED);
    }

    /**
     * Update an existing gadget (Admin only)
     * PUT /api/gadgets/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GadgetDTO> updateGadget(
            @PathVariable Long id,
            @Valid @RequestBody CreateGadgetDTO updateGadgetDTO) {

        GadgetDTO updatedGadget = gadgetService.updateGadget(id, updateGadgetDTO);
        return ResponseEntity.ok(updatedGadget);
    }

    /**
     * Delete a gadget (Admin only)
     * DELETE /api/gadgets/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGadget(@PathVariable Long id) {
        gadgetService.deleteGadget(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get specifications for a gadget
     * GET /api/gadgets/{id}/specifications
     */
    @GetMapping("/{id}/specifications")
    public ResponseEntity<List<SpecificationDTO>> getGadgetSpecifications(@PathVariable Long id) {
        List<SpecificationDTO> specifications = gadgetService.getGadgetSpecifications(id);
        return ResponseEntity.ok(specifications);
    }

    /**
     * Add specification to a gadget (Admin only)
     * POST /api/gadgets/{id}/specifications
     */
    @PostMapping("/{id}/specifications")
    public ResponseEntity<SpecificationDTO> addSpecification(
            @PathVariable Long id,
            @RequestBody SpecificationRequest request) {

        SpecificationDTO specification = gadgetService.addSpecification(
            id, request.getSpecName(), request.getSpecValue());
        return new ResponseEntity<>(specification, HttpStatus.CREATED);
    }

    /**
     * Get available brands by category
     * GET /api/gadgets/brands?category=mobiles
     */
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrandsByCategory(
            @RequestParam(required = false) String category) {

        List<String> brands = gadgetService.getBrandsByCategory(category);
        return ResponseEntity.ok(brands);
    }

    /**
     * Inner class for specification request body
     */
    public static class SpecificationRequest {
        private String specName;
        private String specValue;

        // Getters and Setters
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
    }
}