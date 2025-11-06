package com.techgo.repository;

import com.techgo.model.Category;
import com.techgo.model.Gadget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Gadget entity operations.
 * Provides methods for CRUD operations and custom queries for gadgets.
 */
@Repository
public interface GadgetRepository extends JpaRepository<Gadget, Long> {

    /**
     * Find gadgets by category with pagination
     */
    Page<Gadget> findByCategory(Category category, Pageable pageable);

    /**
     * Find gadgets by brand
     */
    List<Gadget> findByBrand(String brand);

    /**
     * Find gadgets by brand with pagination
     */
    Page<Gadget> findByBrand(String brand, Pageable pageable);

    /**
     * Find gadgets by category and brand
     */
    Page<Gadget> findByCategoryAndBrand(Category category, String brand, Pageable pageable);

    /**
     * Find gadgets within price range
     */
    Page<Gadget> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    /**
     * Find gadgets with minimum rating
     */
    Page<Gadget> findByRatingGreaterThanEqual(BigDecimal minRating, Pageable pageable);

    /**
     * Search gadgets by name (case-insensitive)
     */
    @Query("SELECT g FROM Gadget g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Gadget> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Search gadgets by name and category
     */
    @Query("SELECT g FROM Gadget g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')) AND g.category = :category")
    Page<Gadget> findByNameContainingIgnoreCaseAndCategory(@Param("name") String name, @Param("category") Category category, Pageable pageable);

    /**
     * Search gadgets by name and brand
     */
    @Query("SELECT g FROM Gadget g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')) AND g.brand = :brand")
    Page<Gadget> findByNameContainingIgnoreCaseAndBrand(@Param("name") String name, @Param("brand") String brand, Pageable pageable);

    /**
     * Complex search with multiple filters
     */
    @Query("SELECT g FROM Gadget g WHERE " +
           "(:category IS NULL OR g.category = :category) AND " +
           "(:brand IS NULL OR g.brand = :brand) AND " +
           "(:minPrice IS NULL OR g.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR g.price <= :maxPrice) AND " +
           "(:minRating IS NULL OR g.rating >= :minRating) AND " +
           "(:name IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<Gadget> searchGadgets(@Param("name") String name,
                              @Param("category") Category category,
                              @Param("brand") String brand,
                              @Param("minPrice") BigDecimal minPrice,
                              @Param("maxPrice") BigDecimal maxPrice,
                              @Param("minRating") BigDecimal minRating,
                              Pageable pageable);

    /**
     * Get featured gadgets by category (limited results)
     */
    @Query("SELECT g FROM Gadget g WHERE g.category = :category AND g.rating >= 4.0 ORDER BY g.rating DESC, g.reviewCount DESC")
    List<Gadget> findFeaturedByCategory(@Param("category") Category category, Pageable pageable);

    /**
     * Get all featured gadgets across all categories
     */
    @Query("SELECT g FROM Gadget g WHERE g.rating >= 4.0 ORDER BY g.rating DESC, g.reviewCount DESC")
    List<Gadget> findFeaturedGadgets(Pageable pageable);

    /**
     * Get popular gadgets (most reviews)
     */
    @Query("SELECT g FROM Gadget g ORDER BY g.reviewCount DESC, g.rating DESC")
    List<Gadget> findPopularGadgets(Pageable pageable);

    /**
     * Get latest gadgets (newest first)
     */
    @Query("SELECT g FROM Gadget g ORDER BY g.createdAt DESC")
    List<Gadget> findLatestGadgets(Pageable pageable);

    /**
     * Get gadgets with price in range and category
     */
    @Query("SELECT g FROM Gadget g WHERE g.category = :category AND g.price BETWEEN :minPrice AND :maxPrice ORDER BY g.price ASC")
    Page<Gadget> findByCategoryAndPriceBetween(@Param("category") Category category,
                                             @Param("minPrice") BigDecimal minPrice,
                                             @Param("maxPrice") BigDecimal maxPrice,
                                             Pageable pageable);

    /**
     * Count gadgets by category
     */
    @Query("SELECT COUNT(g) FROM Gadget g WHERE g.category = :category")
    Long countByCategory(@Param("category") Category category);

    /**
     * Count gadgets by brand
     */
    @Query("SELECT COUNT(g) FROM Gadget g WHERE g.brand = :brand")
    Long countByBrand(@Param("brand") String brand);

    /**
     * Get distinct brands for a category
     */
    @Query("SELECT DISTINCT g.brand FROM Gadget g WHERE g.category = :category ORDER BY g.brand")
    List<String> findDistinctBrandsByCategory(@Param("category") Category category);

    /**
     * Get all distinct brands
     */
    @Query("SELECT DISTINCT g.brand FROM Gadget g ORDER BY g.brand")
    List<String> findAllDistinctBrands();

    /**
     * Find gadgets by name or brand (for search autocomplete)
     */
    @Query("SELECT g FROM Gadget g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(g.brand) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Gadget> findByNameOrBrandContainingIgnoreCase(@Param("query") String query, Pageable pageable);

    /**
     * Check if gadget exists with given name and brand
     */
    boolean existsByNameAndBrand(String name, String brand);

    /**
     * Find gadget by name and brand
     */
    Optional<Gadget> findByNameAndBrand(String name, String brand);
}