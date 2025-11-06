package com.techgo.service;

import com.techgo.dto.CreateGadgetDTO;
import com.techgo.dto.GadgetDTO;
import com.techgo.dto.SpecificationDTO;
import com.techgo.exception.BadRequestException;
import com.techgo.exception.ResourceNotFoundException;
import com.techgo.model.Category;
import com.techgo.model.Gadget;
import com.techgo.model.GadgetSpecification;
import com.techgo.repository.GadgetRepository;
import com.techgo.repository.GadgetSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing gadgets in the TechGo application.
 * Provides business logic for CRUD operations and searches.
 */
@Service
@Transactional
public class GadgetService {

    private final GadgetRepository gadgetRepository;
    private final GadgetSpecificationRepository specificationRepository;

    public GadgetService(GadgetRepository gadgetRepository,
                        GadgetSpecificationRepository specificationRepository) {
        this.gadgetRepository = gadgetRepository;
        this.specificationRepository = specificationRepository;
    }

    /**
     * Get all gadgets with pagination and filtering
     */
    @Transactional(readOnly = true)
    public Page<GadgetDTO> getAllGadgets(String category, String brand,
                                        BigDecimal minPrice, BigDecimal maxPrice,
                                        BigDecimal minRating, String search,
                                        int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Gadget> gadgets;

        if (search != null && !search.trim().isEmpty()) {
            // Search with filters
            Category cat = category != null ? Category.fromValue(category) : null;
            gadgets = gadgetRepository.searchGadgets(search, cat, brand, minPrice, maxPrice, minRating, pageable);
        } else if (hasFilters(category, brand, minPrice, maxPrice, minRating)) {
            // Apply filters without search
            Category cat = category != null ? Category.fromValue(category) : null;
            gadgets = gadgetRepository.searchGadgets(null, cat, brand, minPrice, maxPrice, minRating, pageable);
        } else {
            // Get all gadgets
            gadgets = gadgetRepository.findAll(pageable);
        }

        return gadgets.map(this::convertToDTO);
    }

    /**
     * Get gadget by ID
     */
    @Transactional(readOnly = true)
    public GadgetDTO getGadgetById(Long id) {
        Gadget gadget = gadgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gadget", "id", id));
        return convertToDTO(gadget);
    }

    /**
     * Create a new gadget
     */
    public GadgetDTO createGadget(CreateGadgetDTO createGadgetDTO) {
        // Check if gadget already exists
        if (gadgetRepository.existsByNameAndBrand(createGadgetDTO.getName(), createGadgetDTO.getBrand())) {
            throw new BadRequestException("Gadget with name '" + createGadgetDTO.getName() +
                                         "' and brand '" + createGadgetDTO.getBrand() + "' already exists");
        }

        Gadget gadget = new Gadget(
            createGadgetDTO.getName(),
            createGadgetDTO.getBrand(),
            createGadgetDTO.getCategory(),
            createGadgetDTO.getPrice()
        );

        gadget.setDescription(createGadgetDTO.getDescription());
        gadget.setImageUrl(createGadgetDTO.getImageUrl());

        // Save gadget first
        gadget = gadgetRepository.save(gadget);

        // Add specifications if provided
        if (createGadgetDTO.getSpecifications() != null && !createGadgetDTO.getSpecifications().isEmpty()) {
            for (var specDTO : createGadgetDTO.getSpecifications()) {
                GadgetSpecification spec = new GadgetSpecification(gadget, specDTO.getSpecName(), specDTO.getSpecValue());
                specificationRepository.save(spec);
            }
        }

        // Reload gadget with specifications
        return convertToDTO(gadgetRepository.findById(gadget.getId()).orElse(gadget));
    }

    /**
     * Update an existing gadget
     */
    public GadgetDTO updateGadget(Long id, CreateGadgetDTO updateGadgetDTO) {
        Gadget existingGadget = gadgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gadget", "id", id));

        // Check if another gadget with same name and brand exists
        Optional<Gadget> duplicate = gadgetRepository.findByNameAndBrand(
            updateGadgetDTO.getName(), updateGadgetDTO.getBrand());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new BadRequestException("Another gadget with name '" + updateGadgetDTO.getName() +
                                         "' and brand '" + updateGadgetDTO.getBrand() + "' already exists");
        }

        // Update fields
        existingGadget.setName(updateGadgetDTO.getName());
        existingGadget.setBrand(updateGadgetDTO.getBrand());
        existingGadget.setCategory(updateGadgetDTO.getCategory());
        existingGadget.setPrice(updateGadgetDTO.getPrice());
        existingGadget.setDescription(updateGadgetDTO.getDescription());
        existingGadget.setImageUrl(updateGadgetDTO.getImageUrl());

        // Save updated gadget
        existingGadget = gadgetRepository.save(existingGadget);

        return convertToDTO(existingGadget);
    }

    /**
     * Delete a gadget
     */
    public void deleteGadget(Long id) {
        Gadget gadget = gadgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gadget", "id", id));
        gadgetRepository.delete(gadget);
    }

    /**
     * Search gadgets by name
     */
    @Transactional(readOnly = true)
    public Page<GadgetDTO> searchGadgets(String query, String category, String brand,
                                        int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Gadget> gadgets;

        Category cat = category != null ? Category.fromValue(category) : null;

        if (cat != null && brand != null) {
            gadgets = gadgetRepository.findByNameContainingIgnoreCaseAndBrand(query, brand, pageable);
        } else if (cat != null) {
            gadgets = gadgetRepository.findByNameContainingIgnoreCaseAndCategory(query, cat, pageable);
        } else {
            gadgets = gadgetRepository.findByNameContainingIgnoreCase(query, pageable);
        }

        return gadgets.map(this::convertToDTO);
    }

    /**
     * Get featured gadgets
     */
    @Transactional(readOnly = true)
    public List<GadgetDTO> getFeaturedGadgets(String category, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Gadget> gadgets;

        if (category != null) {
            Category cat = Category.fromValue(category);
            gadgets = gadgetRepository.findFeaturedByCategory(cat, pageable);
        } else {
            gadgets = gadgetRepository.findFeaturedGadgets(pageable);
        }

        return gadgets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get gadget specifications
     */
    @Transactional(readOnly = true)
    public List<SpecificationDTO> getGadgetSpecifications(Long gadgetId) {
        if (!gadgetRepository.existsById(gadgetId)) {
            throw new ResourceNotFoundException("Gadget", "id", gadgetId);
        }

        List<GadgetSpecification> specs = specificationRepository.findByGadgetId(gadgetId);
        return specs.stream()
                .map(this::convertToSpecificationDTO)
                .collect(Collectors.toList());
    }

    /**
     * Add specification to gadget
     */
    public SpecificationDTO addSpecification(Long gadgetId, String specName, String specValue) {
        Gadget gadget = gadgetRepository.findById(gadgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Gadget", "id", gadgetId));

        // Check if specification already exists
        if (specificationRepository.findByGadgetIdAndSpecName(gadgetId, specName) != null) {
            throw new BadRequestException("Specification '" + specName + "' already exists for this gadget");
        }

        GadgetSpecification spec = new GadgetSpecification(gadget, specName, specValue);
        spec = specificationRepository.save(spec);

        return convertToSpecificationDTO(spec);
    }

    /**
     * Get distinct brands by category
     */
    @Transactional(readOnly = true)
    public List<String> getBrandsByCategory(String category) {
        if (category != null) {
            return gadgetRepository.findDistinctBrandsByCategory(category);
        }
        return gadgetRepository.findAllDistinctBrands();
    }

    // Helper methods
    private boolean hasFilters(String category, String brand, BigDecimal minPrice, BigDecimal maxPrice, BigDecimal minRating) {
        return category != null || brand != null || minPrice != null || maxPrice != null || minRating != null;
    }

    private GadgetDTO convertToDTO(Gadget gadget) {
        GadgetDTO dto = new GadgetDTO();
        dto.setId(gadget.getId());
        dto.setName(gadget.getName());
        dto.setBrand(gadget.getBrand());
        dto.setCategory(gadget.getCategory());
        dto.setPrice(gadget.getPrice());
        dto.setDescription(gadget.getDescription());
        dto.setImageUrl(gadget.getImageUrl());
        dto.setRating(gadget.getRating());
        dto.setReviewCount(gadget.getReviewCount());
        dto.setCreatedAt(gadget.getCreatedAt());
        dto.setUpdatedAt(gadget.getUpdatedAt());

        // Load specifications
        List<SpecificationDTO> specs = specificationRepository.findByGadgetId(gadget.getId())
                .stream()
                .map(this::convertToSpecificationDTO)
                .collect(Collectors.toList());
        dto.setSpecifications(specs);

        return dto;
    }

    private SpecificationDTO convertToSpecificationDTO(GadgetSpecification spec) {
        return new SpecificationDTO(
            spec.getId(),
            spec.getSpecName(),
            spec.getSpecValue(),
            spec.getCreatedAt()
        );
    }
}