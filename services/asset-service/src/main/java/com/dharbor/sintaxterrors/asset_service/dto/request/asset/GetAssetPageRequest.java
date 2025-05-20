package com.dharbor.sintaxterrors.asset_service.dto.request.asset;

import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.exception.constant.PaginationExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GetAssetPageRequest {
    @Min(value = 1, message = PaginationExceptionConstants.INVALID_PAGE_MESSAGE)
    private Integer page;

    @Min(value = 1, message = PaginationExceptionConstants.INVALID_SIZE_MESSAGE)
    private Integer size;

    private String order;

    @Enumerated(EnumType.STRING)
    private SortDirection direction;

    private String serialNumber;

    private String model;

    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private Long categoryId;

    private Long workspaceId;

    private LocalDate warrantyExpirationFrom;

    private LocalDate warrantyExpirationTo;

    private Boolean underWarranty;

    private String search;

    private BoliviaCity city;
}
