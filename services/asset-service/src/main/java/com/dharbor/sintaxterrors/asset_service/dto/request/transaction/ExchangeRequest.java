package com.dharbor.sintaxterrors.asset_service.dto.request.transaction;

import com.dharbor.sintaxterrors.asset_service.exception.constant.TransactionExceptionConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRequest {
    @NotNull(message = TransactionExceptionConstants.NOT_NULL_EMPLOYEE_ID)
    private Long employeeId;

    @NotNull(message = TransactionExceptionConstants.EMPTY_ASSET_LIST)
    private List<Long> returnedAssetIds;

    @Size(max = 255, message = TransactionExceptionConstants.MAX_LENGTH_REASON)
    private String returnReason;

    @NotNull(message = TransactionExceptionConstants.NOT_NULL_ASSET_IDS)
    private List<Long> newAssetIds;

    @Size(max = 255, message = TransactionExceptionConstants.MAX_LENGTH_REASON)
    private String assignmentReason;

    @Size(max = 500, message = TransactionExceptionConstants.MAX_LENGTH_NOTE)
    private String note;
}
