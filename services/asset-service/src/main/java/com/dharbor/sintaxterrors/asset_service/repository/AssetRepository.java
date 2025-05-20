package com.dharbor.sintaxterrors.asset_service.repository;

import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends
        JpaRepository<AssetEntity, Long>,
        JpaSpecificationExecutor<AssetEntity> {

    Optional<AssetEntity> findById(@NonNull Long assetId);

    Optional<AssetEntity> findBySerialNumber(@NonNull String serialNumber);

    Page<AssetEntity> findAll(Pageable pageable);

    List<AssetEntity> findAll(Specification<AssetEntity> spec);

    @Query("SELECT a FROM AssetEntity a WHERE a.category.id = :categoryId")
    List<AssetEntity> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT a FROM AssetEntity a WHERE a.workspace.id = :workspaceId")
    List<AssetEntity> findByWorkspaceId(@Param("workspaceId") Long workspaceId);

    @Query("SELECT a FROM AssetEntity a WHERE a.status = :status")
    List<AssetEntity> findByStatus(@Param("status") String status);

    @Query("SELECT a FROM AssetEntity a WHERE a.id IN :idList")
    List<AssetEntity> findByIdIn(@Param("idList") List<Long> idList);

    @Query("SELECT a FROM AssetEntity a WHERE a.warrantyExpiration BETWEEN :startDate AND :endDate")
    List<AssetEntity> findAssetsWithWarrantyExpiringBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
