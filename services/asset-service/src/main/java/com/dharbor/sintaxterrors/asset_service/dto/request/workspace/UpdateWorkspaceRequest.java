package com.dharbor.sintaxterrors.asset_service.dto.request.workspace;


import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.exception.constant.WorkspaceExceptionConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkspaceRequest {

    @NotNull(message = WorkspaceExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Long id;

    @NotNull(message = WorkspaceExceptionConstants.NOT_NULL_CITY_MESSAGE)
    private BoliviaCity city;

    @NotEmpty(message = WorkspaceExceptionConstants.NOT_EMPTY_LOCATION_MESSAGE)
    private String location;
}
