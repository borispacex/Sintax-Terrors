package com.dharbor.sintaxterrors.asset_service.dto.request.transaction;

import com.dharbor.sintaxterrors.asset_service.exception.constant.TransactionExceptionConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReturnRequest {
    @NotNull(message = TransactionExceptionConstants.NOT_NULL_EMPLOYEE_ID)
    private Long employeeId;

    @NotNull(message = TransactionExceptionConstants.NOT_NULL_ASSET_IDS)
    private List<Long> assetIds;

    @Size(max = 255, message = TransactionExceptionConstants.MAX_LENGTH_REASON)
    private String reason;

    @Size(max = 500, message = TransactionExceptionConstants.MAX_LENGTH_NOTE)
    private String note;
}