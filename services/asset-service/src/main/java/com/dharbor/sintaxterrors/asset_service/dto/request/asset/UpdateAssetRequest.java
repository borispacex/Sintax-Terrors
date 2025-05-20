package com.dharbor.sintaxterrors.asset_service.dto.request.asset;

import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateAssetRequest {
    @NotNull(message = AssetExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Long id;

    @NotBlank(message = AssetExceptionConstants.NOT_BLANK_SERIAL_NUMBER_MESSAGE)
    @Size(max = 100, message = AssetExceptionConstants.MAX_LENGTH_SERIAL_NUMBER_MESSAGE)
    private String serialNumber;

    @Size(max = 100, message = AssetExceptionConstants.MAX_LENGTH_MODEL_MESSAGE)
    private String model;

    @Size(max = 100, message = AssetExceptionConstants.MAX_LENGTH_MANUFACTURER_MESSAGE)
    private String manufacturer;

    @NotNull(message = AssetExceptionConstants.NOT_NULL_CATEGORY_MESSAGE)
    private Long categoryId;

    private Long workspaceId;

    @PastOrPresent(message = AssetExceptionConstants.INVALID_PURCHASE_DATE_MESSAGE)
    private LocalDate purchaseDate;

    @DecimalMin(value = "0.0", message = AssetExceptionConstants.INVALID_PURCHASE_COST_MESSAGE)
    private BigDecimal purchaseCost;

    @NotBlank(message = AssetExceptionConstants.NOT_BLANK_STATUS_MESSAGE)
    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private LocalDate warrantyExpiration;

    private String notes;
}
