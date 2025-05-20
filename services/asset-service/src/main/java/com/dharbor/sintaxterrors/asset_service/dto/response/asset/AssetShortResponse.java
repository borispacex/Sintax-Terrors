package com.dharbor.sintaxterrors.asset_service.dto.response.asset;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssetShortResponse {
    private Long id;

    private String serialNumber;

    private String model;

    private String manufacturer;

    private String status;

    private Long categoryId;

    private String categoryName;

    private Long workspaceId;

    private String workspaceName;

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiration;

    private Boolean underWarranty;
}
