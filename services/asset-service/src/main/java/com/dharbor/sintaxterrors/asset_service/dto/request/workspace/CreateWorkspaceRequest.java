package com.dharbor.sintaxterrors.asset_service.dto.request.workspace;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.exception.constant.WorkspaceExceptionConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateWorkspaceRequest {

    @NotNull(message = WorkspaceExceptionConstants.NOT_NULL_CITY_MESSAGE)
    private BoliviaCity city;

    @NotEmpty(message = WorkspaceExceptionConstants.NOT_EMPTY_LOCATION_MESSAGE)
    private String location;
}
