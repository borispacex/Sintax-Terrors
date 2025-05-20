package com.dharbor.sintaxterrors.asset_service.dto.response.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CurrentAssignmentsResponse {

    private Long employeeId;

    private String employeeName;

    private List<AssignedAsset> assets;

    @Getter
    @Setter
    public static class AssignedAsset {

        private Long assetId;

        private String serialNumber;

        private String model;

        private LocalDateTime assignmentDate;
    }
}
