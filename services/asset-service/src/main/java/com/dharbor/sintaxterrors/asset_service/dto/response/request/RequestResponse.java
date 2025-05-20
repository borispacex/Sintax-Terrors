package com.dharbor.sintaxterrors.asset_service.dto.response.request;

import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.enums.request.RequestType;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestResponse {
    private Integer id;

    private String title;

    private String description;

    private Status status;

    private LocalDate createdAt;

    private RequestType type;

    private String assets;

    private EmployeeResponse employee;
}
