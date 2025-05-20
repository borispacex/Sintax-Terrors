package com.dharbor.sintaxterrors.asset_service.dto.response.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransactionResponse {

    private Long id;

    private String transactionType;

    private LocalDateTime transactionDate;

    private Long employeeId;

    private String employeeName;

    private String note;

    private List<TransactionDetailResponse> details;
}
