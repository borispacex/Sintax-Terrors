package com.dharbor.sintaxterrors.asset_service.dto.response.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionWithDetailsResponse {

    private TransactionResponse transaction;

    private List<TransactionDetailResponse> details;

    public TransactionWithDetailsResponse(TransactionResponse transaction,
                                          List<TransactionDetailResponse> details) {
        this.transaction = transaction;
        this.details = details;
    }
}
