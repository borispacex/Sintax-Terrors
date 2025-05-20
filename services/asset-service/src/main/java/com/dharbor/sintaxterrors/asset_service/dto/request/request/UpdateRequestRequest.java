package com.dharbor.sintaxterrors.asset_service.dto.request.request;

import com.dharbor.sintaxterrors.asset_service.enums.request.RequestType;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import com.dharbor.sintaxterrors.asset_service.exception.constant.RequestExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TeamExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRequestRequest {
    @NotNull(message = TeamExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Integer id;

    @NotEmpty(message = RequestExceptionConstants.NOT_EMPTY_TITLE_MESSAGE)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = RequestExceptionConstants.NOT_NULL_TYPE_MESSAGE)
    private RequestType type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String assetIds;

    @NotNull(message = RequestExceptionConstants.NOT_NULL_EMPLOYEE_ID_MESSAGE)
    private Integer employeeId;
}