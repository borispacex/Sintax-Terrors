package com.dharbor.sintaxterrors.asset_service.dto.request.team;

import com.dharbor.sintaxterrors.asset_service.exception.constant.TeamExceptionConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTeamRequest {
    @NotNull(message = TeamExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Integer id;

    @NotEmpty(message = TeamExceptionConstants.NOT_EMPTY_NAME_MESSAGE)
    private String name;

    private String description;

    private Boolean isActive;

    @NotNull(message = TeamExceptionConstants.NOT_NULL_PROJECT_MANAGER_ID_MESSAGE)
    private Integer projectManagerId;
}