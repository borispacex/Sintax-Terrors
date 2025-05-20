package com.dharbor.sintaxterrors.asset_service.dto.response.asset;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AssetFinancialResponse {

    private Long id;

    private String serialNumber;

    private String model;

    private String manufacturer;

    private LocalDate purchaseDate;

    private BigDecimal purchaseCost;

    private BigDecimal currentValue;

    private BigDecimal depreciationValue;

    private BigDecimal netBookValue;

    private Integer usefulLifeMonths;

    private Integer remainingLifeMonths;

    private String status;
}
