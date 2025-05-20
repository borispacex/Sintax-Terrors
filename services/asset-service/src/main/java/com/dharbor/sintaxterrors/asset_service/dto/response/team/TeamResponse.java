package com.dharbor.sintaxterrors.asset_service.dto.response.team;

import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponse {
    private Integer id;

    private String name;

    private String description;

    private Boolean isActive;

    private EmployeeShortResponse projectManager;
}
