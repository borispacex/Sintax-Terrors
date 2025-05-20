package com.dharbor.sintaxterrors.asset_service.dto.request.transaction;

import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.exception.constant.PaginationExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetTransactionPageRequest {
    @Min(value = 1, message = PaginationExceptionConstants.INVALID_PAGE_MESSAGE)
    private Integer page;

    @Min(value = 1, message = PaginationExceptionConstants.INVALID_SIZE_MESSAGE)
    private Integer size;

    private String order;

    @Enumerated(EnumType.STRING)
    private SortDirection direction;

    private Long employeeId;

    private Long assetId;

    private TransactionType transactionType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String note;
}
