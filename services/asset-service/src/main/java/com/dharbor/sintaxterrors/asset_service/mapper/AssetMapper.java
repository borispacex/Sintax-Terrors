package com.dharbor.sintaxterrors.asset_service.mapper;

import com.dharbor.sintaxterrors.asset_service.dto.request.asset.CreateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.UpdateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetFinancialResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetShortResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {ResponseConstant.SuccessResponse.class})
public interface AssetMapper {

    default AssetEntity mapperToAssetEntity(CreateAssetRequest source) {
        AssetEntity target = new AssetEntity();
        target.setSerialNumber(source.getSerialNumber().toUpperCase().trim());
        target.setModel(source.getModel() != null ? source.getModel().trim() : null);
        target.setManufacturer(source.getManufacturer() != null ? source.getManufacturer().trim() : null);
        target.setPurchaseDate(source.getPurchaseDate());
        target.setPurchaseCost(source.getPurchaseCost());
        target.setStatus(source.getStatus());
        target.setWarrantyExpiration(source.getWarrantyExpiration());
        target.setNotes(source.getNotes() != null ? source.getNotes().trim() : null);
        return target;
    }

    default void updateAssetEntity(UpdateAssetRequest source, @MappingTarget AssetEntity target) {
        if (source.getSerialNumber() != null) {
            target.setSerialNumber(source.getSerialNumber().toUpperCase().trim());
        }
        if (source.getModel() != null) {
            target.setModel(source.getModel().trim());
        }
        if (source.getManufacturer() != null) {
            target.setManufacturer(source.getManufacturer().trim());
        }
        if (source.getPurchaseDate() != null) {
            target.setPurchaseDate(source.getPurchaseDate());
        }
        if (source.getPurchaseCost() != null) {
            target.setPurchaseCost(source.getPurchaseCost());
        }
        if (source.getStatus() != null) {
            target.setStatus(source.getStatus());
        }
        if (source.getWarrantyExpiration() != null) {
            target.setWarrantyExpiration(source.getWarrantyExpiration());
        }
        if (source.getNotes() != null) {
            target.setNotes(source.getNotes().trim());
        }
    }

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "workspaceId", source = "workspace.id")
    @Mapping(target = "location", source = "workspace.location")
    @Mapping(target = "ageInMonths", expression = "java(calculateAgeInMonths(source.getPurchaseDate()))")
    @Mapping(target = "underWarranty", expression = "java(isUnderWarranty(source.getWarrantyExpiration()))")
    AssetResponse mapperToAssetResponse(AssetEntity source);

    AssetShortResponse mapperToShortAssetResponse(AssetEntity source);

    default long calculateAgeInMonths(LocalDate purchaseDate) {
        if (purchaseDate == null) return 0;
        return ChronoUnit.MONTHS.between(purchaseDate, LocalDate.now());
    }

    default boolean isUnderWarranty(LocalDate warrantyExpiration) {
        return warrantyExpiration != null && warrantyExpiration.isAfter(LocalDate.now());
    }

    @Mapping(target = "depreciationValue", expression = "java(calculateDepreciation(source))")
    @Mapping(target = "netBookValue", expression = "java(calculateNetBookValue(source))")
    @Mapping(target = "usefulLifeMonths", source = "category.usefulLifeMonths")
    @Mapping(target = "remainingLifeMonths", expression = "java(calculateRemainingLifeMonths(source))")
    AssetFinancialResponse mapperToFinancialResponse(AssetEntity source);

    default BigDecimal calculateDepreciation(AssetEntity asset) {
        if (!isDepreciable(asset)) {
            return BigDecimal.ZERO;
        }

        long monthsInUse = getMonthsInUse(asset);
        if (monthsInUse <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal monthlyDepreciation = asset.getPurchaseCost()
                .divide(BigDecimal.valueOf(asset.getCategory().getUsefulLifeMonths()), 2, RoundingMode.HALF_UP);

        return monthlyDepreciation.multiply(BigDecimal.valueOf(monthsInUse));
    }

    default BigDecimal calculateNetBookValue(AssetEntity asset) {
        if (asset.getPurchaseCost() == null) {
            return BigDecimal.ZERO;
        }
        return asset.getPurchaseCost().subtract(calculateDepreciation(asset));
    }

    default Integer calculateRemainingLifeMonths(AssetEntity asset) {
        if (!isDepreciable(asset)) {
            return 0;
        }
        long monthsInUse = getMonthsInUse(asset);
        return Math.max(0, asset.getCategory().getUsefulLifeMonths() - (int) monthsInUse);
    }

    private boolean isDepreciable(AssetEntity asset) {
        return asset.getCategory() != null &&
                asset.getCategory().getIsDepreciable() != null &&
                asset.getCategory().getIsDepreciable() &&
                asset.getCategory().getUsefulLifeMonths() != null &&
                asset.getCategory().getUsefulLifeMonths() > 0 &&
                asset.getPurchaseCost() != null &&
                asset.getPurchaseDate() != null;
    }

    private long getMonthsInUse(AssetEntity asset) {
        return ChronoUnit.MONTHS.between(
                asset.getPurchaseDate(),
                LocalDate.now()
        );
    }
}
