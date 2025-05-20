package com.dharbor.sintaxterrors.asset_service.dto.response.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AssetAssignmentHistoryResponse {

    private Long assetId;

    private String assetSerialNumber;

    private List<AssignmentRecord> assignments;

    @Getter
    @Setter
    public static class AssignmentRecord {

        private Long transactionId;

        private LocalDateTime date;

        private Long employeeId;

        private String employeeName;

        private String type;

        private String direction;
    }
}
