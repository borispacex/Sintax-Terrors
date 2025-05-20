package com.dharbor.sintaxterrors.asset_service.dto.response.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailResponse {

    private Long id;

    private Long transactionId;

    private Long assetId;

    private String assetSerialNumber;

    private String assetModel;

    private String direction;

    private String reason;
}
