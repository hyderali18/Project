package com.techgo.repository;

import com.techgo.model.Gadget;
import com.techgo.model.GadgetSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for GadgetSpecification entity operations.
 * Provides methods for CRUD operations and custom queries for gadget specifications.
 */
@Repository
public interface GadgetSpecificationRepository extends JpaRepository<GadgetSpecification, Long> {

    /**
     * Find all specifications for a gadget
     */
    List<GadgetSpecification> findByGadget(Gadget gadget);

    /**
     * Find specifications by gadget ID
     */
    List<GadgetSpecification> findByGadgetId(Long gadgetId);

    /**
     * Find specific specification by gadget and spec name
     */
    GadgetSpecification findByGadgetAndSpecName(Gadget gadget, String specName);

    /**
     * Find specific specification by gadget ID and spec name
     */
    GadgetSpecification findByGadgetIdAndSpecName(Long gadgetId, String specName);

    /**
     * Find all gadgets that have a specific specification value
     */
    @Query("SELECT gs.gadget FROM GadgetSpecification gs WHERE gs.specName = :specName AND gs.specValue LIKE %:specValue%")
    List<Gadget> findGadgetsBySpecification(@Param("specName") String specName, @Param("specValue") String specValue);

    /**
     * Find all unique specification names for a category
     */
    @Query("SELECT DISTINCT gs.specName FROM GadgetSpecification gs WHERE gs.gadget.category = :category ORDER BY gs.specName")
    List<String> findDistinctSpecNamesByCategory(@Param("category") String category);

    /**
     * Find all possible values for a specification name
     */
    @Query("SELECT DISTINCT gs.specValue FROM GadgetSpecification gs WHERE gs.specName = :specName ORDER BY gs.specValue")
    List<String> findDistinctSpecValuesByName(@Param("specName") String specName);

    /**
     * Count specifications for a gadget
     */
    @Query("SELECT COUNT(gs) FROM GadgetSpecification gs WHERE gs.gadget = :gadget")
    Long countByGadget(@Param("gadget") Gadget gadget);

    /**
     * Delete all specifications for a gadget
     */
    void deleteByGadget(Gadget gadget);

    /**
     * Find specifications with spec names containing search term
     */
    @Query("SELECT gs FROM GadgetSpecification gs WHERE LOWER(gs.specName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<GadgetSpecification> findBySpecNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    /**
     * Find specifications with spec values containing search term
     */
    @Query("SELECT gs FROM GadgetSpecification gs WHERE LOWER(gs.specValue) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<GadgetSpecification> findBySpecValueContainingIgnoreCase(@Param("searchTerm") String searchTerm);
}