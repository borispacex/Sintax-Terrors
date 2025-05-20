package com.dharbor.sintaxterrors.asset_service.repository;


import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends
        JpaRepository<TransactionEntity, Long>,
        JpaSpecificationExecutor<TransactionEntity> {

    Optional<TransactionEntity> findById(@NonNull Long transactionId);

    Page<TransactionEntity> findAll(Pageable pageable);

    List<TransactionEntity> findAll(Specification<TransactionEntity> spec);

    @Query("SELECT t FROM TransactionEntity t WHERE t.employee.id = :employeeId")
    List<TransactionEntity> findByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT t FROM TransactionEntity t WHERE t.transactionType = :transactionType")
    List<TransactionEntity> findByTransactionType(@Param("transactionType") String transactionType);

    @Query("SELECT t FROM TransactionEntity t WHERE t.transactionDate BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM TransactionEntity t WHERE t.employee.id = :employeeId AND t.transactionType = 'ASSIGNMENT'")
    List<TransactionEntity> findAssignmentsByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT td.asset FROM TransactionDetailEntity td " +
            "WHERE td.transaction.employee.id = :employeeId " +
            "AND td.direction = 'OUT' " +
            "AND td.asset.id NOT IN (" +
            "   SELECT td2.asset.id FROM TransactionDetailEntity td2 " +
            "   WHERE td2.direction = 'IN' AND td2.transaction.transactionDate > td.transaction.transactionDate" +
            ")")
    List<AssetEntity> findCurrentlyAssignedAssetsByEmployee(@Param("employeeId") Long employeeId);

    @Query("SELECT t FROM TransactionEntity t " +
            "WHERE t.id IN (SELECT td.transaction.id FROM TransactionDetailEntity td WHERE td.asset.id = :assetId) " +
            "ORDER BY t.transactionDate DESC")
    List<TransactionEntity> findAssetMovementHistory(@Param("assetId") Long assetId);
}
