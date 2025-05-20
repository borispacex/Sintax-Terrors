package com.dharbor.sintaxterrors.asset_service.repository;

import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends
        JpaRepository<TransactionDetailEntity, Long> {

    @Query("SELECT td FROM TransactionDetailEntity td WHERE td.transaction.id = :transactionId")
    List<TransactionDetailEntity> findByTransactionId(@Param("transactionId") Long transactionId);

    @Query("SELECT td FROM TransactionDetailEntity td WHERE td.asset.id = :assetId")
    List<TransactionDetailEntity> findByAssetId(@Param("assetId") Long assetId);

    @Query("SELECT td FROM TransactionDetailEntity td " +
            "WHERE td.transaction.id = :transactionId AND td.direction = :direction")
    List<TransactionDetailEntity> findByTransactionIdAndDirection(
            @Param("transactionId") Long transactionId,
            @Param("direction") String direction);

    @Query("SELECT td FROM TransactionDetailEntity td " +
            "WHERE td.transaction.id = :transactionId " +
            "ORDER BY td.direction DESC")
    List<TransactionDetailEntity> findExchangeDetails(@Param("transactionId") Long transactionId);
}
