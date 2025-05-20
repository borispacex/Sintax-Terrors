package com.dharbor.sintaxterrors.asset_service.model.entity.asset;

import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.math.BigDecimal;
import java.time.LocalDate;

@StaticMetamodel(AssetEntity.class)
public class AssetEntity_ {

    public static volatile SingularAttribute<AssetEntity, Double> id;

    public static volatile SingularAttribute<AssetEntity, String> serialNumber;

    public static volatile SingularAttribute<AssetEntity, String> model;

    public static volatile SingularAttribute<AssetEntity, String> manufacturer;

    public static volatile SingularAttribute<AssetEntity, CategoryEntity> category;

    public static volatile SingularAttribute<AssetEntity, WorkspaceEntity> workspace;

    public static volatile SingularAttribute<AssetEntity, LocalDate> purchaseDate;

    public static volatile SingularAttribute<AssetEntity, BigDecimal> purchaseCost;

    public static volatile SingularAttribute<AssetEntity, AssetStatus> status;

    public static volatile SingularAttribute<AssetEntity, LocalDate> warrantyExpiration;

    public static volatile SingularAttribute<AssetEntity, BoliviaCity> city;

    public static volatile SingularAttribute<AssetEntity, String> notes;
}
