package com.dharbor.sintaxterrors.asset_service.model.entity.asset;

import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.model.constant.AssetEntityConstants.AssetTable;
import com.dharbor.sintaxterrors.asset_service.model.constant.WorkspaceEntityConstants;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = AssetTable.NAME)
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = AssetTable.Id.NAME, nullable = false)
    private Double id;

    @Column(name = AssetTable.SerialNumber.NAME, nullable = false, length = AssetTable.SerialNumber.LENGTH)
    private String serialNumber;

    @Column(name = AssetTable.Model.NAME, length = AssetTable.Model.LENGTH)
    private String model;

    @Column(name = AssetTable.Manufacturer.NAME, length = AssetTable.Manufacturer.LENGTH)
    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = AssetTable.CategoryJoin.NAME)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = AssetTable.WorkspaceJoin.NAME)
    private WorkspaceEntity workspace;

    @Column(name = AssetTable.PurchaseDate.NAME)
    private LocalDate purchaseDate;

    @Column(name = AssetTable.PurchaseCost.NAME)
    private BigDecimal purchaseCost;

    @Column(name = AssetTable.Status.NAME, length = AssetTable.Status.LENGTH)
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    @Column(name = AssetTable.WarrantyExpiration.NAME)
    private LocalDate warrantyExpiration;

    @Column(name = WorkspaceEntityConstants.WorkspaceTable.City.NAME, length = WorkspaceEntityConstants.WorkspaceTable.City.LENGTH)
    @Enumerated(EnumType.STRING)
    private BoliviaCity city;

    @Column(name = AssetTable.Notes.NAME)
    private String notes;
}