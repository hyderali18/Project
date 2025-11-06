package com.techgo.repository;

import com.techgo.model.ComparisonItem;
import com.techgo.model.ComparisonList;
import com.techgo.model.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for ComparisonItem entity operations.
 * Provides methods for CRUD operations and custom queries for comparison items.
 */
@Repository
public interface ComparisonItemRepository extends JpaRepository<ComparisonItem, Long> {

    /**
     * Find all items in a comparison list
     */
    List<ComparisonItem> findByComparisonList(ComparisonList comparisonList);

    /**
     * Find all items in a comparison list by comparison ID
     */
    List<ComparisonItem> findByComparisonListId(String comparisonListId);

    /**
     * Find items for a comparison list with gadget details
     */
    @Query("SELECT ci FROM ComparisonItem ci LEFT JOIN FETCH ci.gadget WHERE ci.comparisonList.id = :comparisonListId ORDER BY ci.addedAt ASC")
    List<ComparisonItem> findByComparisonListIdWithGadget(@Param("comparisonListId") String comparisonListId);

    /**
     * Check if a gadget is in a comparison list
     */
    boolean existsByComparisonListAndGadget(ComparisonList comparisonList, Gadget gadget);

    /**
     * Find specific comparison item
     */
    Optional<ComparisonItem> findByComparisonListAndGadget(ComparisonList comparisonList, Gadget gadget);

    /**
     * Count items in a comparison list
     */
    @Query("SELECT COUNT(ci) FROM ComparisonItem ci WHERE ci.comparisonList = :comparisonList")
    Long countByComparisonList(@Param("comparisonList") ComparisonList comparisonList);

    /**
     * Find all comparison lists containing a specific gadget
     */
    @Query("SELECT ci FROM ComparisonItem ci WHERE ci.gadget = :gadget")
    List<ComparisonItem> findByGadget(@Param("gadget") Gadget gadget);

    /**
     * Count how many comparison lists contain a specific gadget
     */
    @Query("SELECT COUNT(ci) FROM ComparisonItem ci WHERE ci.gadget = :gadget")
    Long countByGadget(@Param("gadget") Gadget gadget);

    /**
     * Delete all items in a comparison list
     */
    void deleteByComparisonList(ComparisonList comparisonList);

    /**
     * Remove a specific gadget from a comparison list
     */
    void deleteByComparisonListAndGadget(ComparisonList comparisonList, Gadget gadget);

    /**
     * Find recently added items
     */
    @Query("SELECT ci FROM ComparisonItem ci WHERE ci.comparisonList.id = :comparisonListId ORDER BY ci.addedAt DESC")
    List<ComparisonItem> findByComparisonListIdOrderByAddedAtDesc(@Param("comparisonListId") String comparisonListId);

    /**
     * Check if comparison list has reached maximum capacity
     */
    @Query("SELECT COUNT(ci) >= 3 FROM ComparisonItem ci WHERE ci.comparisonList = :comparisonList")
    boolean isFull(@Param("comparisonList") ComparisonList comparisonList);

    /**
     * Get popular gadgets (most often compared)
     */
    @Query("SELECT ci.gadget, COUNT(ci) as comparisonCount " +
           "FROM ComparisonItem ci " +
           "GROUP BY ci.gadget " +
           "ORDER BY comparisonCount DESC")
    List<Object[]> findMostComparedGadgets();

    /**
     * Get comparison statistics for a gadget
     */
    @Query("SELECT " +
           "COUNT(ci) as totalComparisons, " +
           "COUNT(DISTINCT ci.comparisonList) as uniqueComparisonLists " +
           "FROM ComparisonItem ci " +
           "WHERE ci.gadget = :gadget")
    Object[] getGadgetComparisonStats(@Param("gadget") Gadget gadget);

    /**
     * Find items added in a specific time range
     */
    @Query("SELECT ci FROM ComparisonItem ci WHERE ci.addedAt BETWEEN :startDate AND :endDate")
    List<ComparisonItem> findByAddedAtBetween(@Param("startDate") java.time.LocalDateTime startDate,
                                             @Param("endDate") java.time.LocalDateTime endDate);
}