package com.techgo.repository;

import com.techgo.model.ComparisonList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for ComparisonList entity operations.
 * Provides methods for CRUD operations and custom queries for comparison lists.
 */
@Repository
public interface ComparisonListRepository extends JpaRepository<ComparisonList, String> {

    /**
     * Find comparison list by ID with its items
     */
    @Query("SELECT cl FROM ComparisonList cl LEFT JOIN FETCH cl.items i LEFT JOIN FETCH i.gadget WHERE cl.id = :id")
    Optional<ComparisonList> findByIdWithItems(@Param("id") String id);

    /**
     * Find all comparison lists created after a specific date
     */
    @Query("SELECT cl FROM ComparisonList cl WHERE cl.createdAt >= :date")
    List<ComparisonList> findByCreatedAtAfter(@Param("date") LocalDateTime date);

    /**
     * Find comparison lists that are about to expire (within 24 hours)
     */
    @Query("SELECT cl FROM ComparisonList cl WHERE cl.expiresAt BETWEEN :now AND :expiryLimit")
    List<ComparisonList> findExpiringSoon(@Param("now") LocalDateTime now, @Param("expiryLimit") LocalDateTime expiryLimit);

    /**
     * Find expired comparison lists
     */
    @Query("SELECT cl FROM ComparisonList cl WHERE cl.expiresAt < :now")
    List<ComparisonList> findExpired(@Param("now") LocalDateTime now);

    /**
     * Find comparison lists by name
     */
    List<ComparisonList> findByNameContainingIgnoreCase(String name);

    /**
     * Count active comparison lists (not expired)
     */
    @Query("SELECT COUNT(cl) FROM ComparisonList cl WHERE cl.expiresAt > :now")
    Long countActive(@Param("now") LocalDateTime now);

    /**
     * Count expired comparison lists
     */
    @Query("SELECT COUNT(cl) FROM ComparisonList cl WHERE cl.expiresAt <= :now")
    Long countExpired(@Param("now") LocalDateTime now);

    /**
     * Find comparison lists with minimum number of items
     */
    @Query("SELECT cl FROM ComparisonList cl WHERE SIZE(cl.items) >= :minItems")
    List<ComparisonList> findByMinItems(@Param("minItems") Integer minItems);

    /**
     * Get comparison statistics
     */
    @Query("SELECT " +
           "COUNT(cl) as totalComparisons, " +
           "COUNT(CASE WHEN cl.expiresAt > :now THEN 1 END) as activeComparisons, " +
           "COUNT(CASE WHEN cl.expiresAt <= :now THEN 1 END) as expiredComparisons, " +
           "AVG(SIZE(cl.items)) as avgItemsPerComparison " +
           "FROM ComparisonList cl")
    Object[] getComparisonStatistics(@Param("now") LocalDateTime now);

    /**
     * Find most recent comparison lists
     */
    List<ComparisonList> findTop10ByOrderByCreatedAtDesc();

    /**
     * Clean up expired comparison lists
     */
    @Query("DELETE FROM ComparisonList cl WHERE cl.expiresAt < :date")
    void deleteExpired(@Param("date") LocalDateTime date);

    /**
     * Check if comparison list exists and is not expired
     */
    @Query("SELECT COUNT(cl) > 0 FROM ComparisonList cl WHERE cl.id = :id AND cl.expiresAt > :now")
    boolean existsActiveById(@Param("id") String id, @Param("now") LocalDateTime now);

    /**
     * Find comparison lists containing a specific gadget
     */
    @Query("SELECT DISTINCT cl FROM ComparisonList cl JOIN cl.items i WHERE i.gadget.id = :gadgetId")
    List<ComparisonList> findByGadgetId(@Param("gadgetId") Long gadgetId);
}