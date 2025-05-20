package com.dharbor.sintaxterrors.asset_service.dto.request.workspace;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.exception.constant.PaginationExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetWorkspacePageRequest {
    @Min(value = 1, message = PaginationExceptionConstants.INVALID_PAGE_MESSAGE)
    private Integer page;

    @Min(value = 1, message = PaginationExceptionConstants.INVALID_SIZE_MESSAGE)
    private Integer size;

    private String order;

    @Enumerated(EnumType.STRING)
    private SortDirection direction;

    private String search;

    private BoliviaCity city;

}
