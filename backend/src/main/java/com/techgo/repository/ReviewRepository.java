package com.techgo.repository;

import com.techgo.model.Gadget;
import com.techgo.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Review entity operations.
 * Provides methods for CRUD operations and custom queries for reviews.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Find all reviews for a gadget with pagination
     */
    Page<Review> findByGadget(Gadget gadget, Pageable pageable);

    /**
     * Find all reviews for a gadget by gadget ID with pagination
     */
    Page<Review> findByGadgetId(Long gadgetId, Pageable pageable);

    /**
     * Find reviews for a gadget with minimum rating
     */
    Page<Review> findByGadgetAndRatingGreaterThanEqual(Gadget gadget, Integer minRating, Pageable pageable);

    /**
     * Find reviews by rating across all gadgets
     */
    Page<Review> findByRating(Integer rating, Pageable pageable);

    /**
     * Find reviews by user name
     */
    Page<Review> findByUserNameContainingIgnoreCase(String userName, Pageable pageable);

    /**
     * Find reviews by email
     */
    Page<Review> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    /**
     * Find reviews containing search term in comment
     */
    @Query("SELECT r FROM Review r WHERE LOWER(r.comment) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Review> findByCommentContainingIgnoreCase(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Find reviews by gadget and rating
     */
    Page<Review> findByGadgetAndRating(Gadget gadget, Integer rating, Pageable pageable);

    /**
     * Find reviews created after a specific date
     */
    @Query("SELECT r FROM Review r WHERE r.createdAt >= :date")
    Page<Review> findByCreatedAtAfter(@Param("date") LocalDateTime date, Pageable pageable);

    /**
     * Find reviews created within a date range
     */
    @Query("SELECT r FROM Review r WHERE r.createdAt BETWEEN :startDate AND :endDate")
    Page<Review> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate,
                                       Pageable pageable);

    /**
     * Complex search for reviews
     */
    @Query("SELECT r FROM Review r WHERE " +
           "(:gadgetId IS NULL OR r.gadget.id = :gadgetId) AND " +
           "(:minRating IS NULL OR r.rating >= :minRating) AND " +
           "(:maxRating IS NULL OR r.rating <= :maxRating) AND " +
           "(:userName IS NULL OR LOWER(r.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) AND " +
           "(:email IS NULL OR LOWER(r.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:comment IS NULL OR LOWER(r.comment) LIKE LOWER(CONCAT('%', :comment, '%')))")
    Page<Review> searchReviews(@Param("gadgetId") Long gadgetId,
                              @Param("minRating") Integer minRating,
                              @Param("maxRating") Integer maxRating,
                              @Param("userName") String userName,
                              @Param("email") String email,
                              @Param("comment") String comment,
                              Pageable pageable);

    /**
     * Count reviews for a gadget
     */
    @Query("SELECT COUNT(r) FROM Review r WHERE r.gadget = :gadget")
    Long countByGadget(@Param("gadget") Gadget gadget);

    /**
     * Count reviews by rating for a gadget
     */
    @Query("SELECT COUNT(r) FROM Review r WHERE r.gadget = :gadget AND r.rating = :rating")
    Long countByGadgetAndRating(@Param("gadget") Gadget gadget, @Param("rating") Integer rating);

    /**
     * Calculate average rating for a gadget
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.gadget = :gadget")
    Double calculateAverageRatingByGadget(@Param("gadget") Gadget gadget);

    /**
     * Get latest reviews for a gadget
     */
    @Query("SELECT r FROM Review r WHERE r.gadget = :gadget ORDER BY r.createdAt DESC")
    List<Review> findLatestByGadget(@Param("gadget") Gadget gadget, Pageable pageable);

    /**
     * Get most helpful reviews (highest rated) for a gadget
     */
    @Query("SELECT r FROM Review r WHERE r.gadget = :gadget ORDER BY r.rating DESC, r.createdAt DESC")
    List<Review> findMostHelpfulByGadget(@Param("gadget") Gadget gadget, Pageable pageable);

    /**
     * Find all reviews by a specific user
     */
    Page<Review> findByEmail(String email, Pageable pageable);

    /**
     * Check if user has already reviewed a gadget
     */
    boolean existsByGadgetAndEmail(Gadget gadget, String email);

    /**
     * Find a user's review for a specific gadget
     */
    Review findByGadgetAndEmail(Gadget gadget, String email);

    /**
     * Delete all reviews for a gadget
     */
    void deleteByGadget(Gadget gadget);

    /**
     * Get review statistics for a gadget
     */
    @Query("SELECT " +
           "r.rating as rating, " +
           "COUNT(r) as count " +
           "FROM Review r " +
           "WHERE r.gadget = :gadget " +
           "GROUP BY r.rating " +
           "ORDER BY r.rating DESC")
    List<Object[]> getRatingDistributionByGadget(@Param("gadget") Gadget gadget);
}