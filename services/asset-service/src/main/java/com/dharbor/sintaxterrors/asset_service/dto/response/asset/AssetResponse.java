package com.dharbor.sintaxterrors.asset_service.dto.response.asset;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AssetResponse {

    private Long id;

    private String serialNumber;

    private String model;

    private String manufacturer;

    private Long categoryId;

    private String categoryName;

    private Long workspaceId;

    private String location;

    private LocalDate purchaseDate;

    private BigDecimal purchaseCost;

    private String status;

    private LocalDate warrantyExpiration;

    private String notes;

    private Long ageInMonths;

    private Boolean underWarranty;
}
