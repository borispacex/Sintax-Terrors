package com.dharbor.sintaxterrors.dto.request;

import com.dharbor.sintaxterrors.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.exception.constant.PaginationExceptionConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GetNotificationPageRequest {
    @Min(value = 1, message = PaginationExceptionConstants.INVALID_PAGE_MESSAGE)
    private Integer page;

    @Min(value = 1, message = PaginationExceptionConstants.INVALID_SIZE_MESSAGE)
    private Integer size;

    private String order;

    @Enumerated(EnumType.STRING)
    private SortDirection direction;

    private Integer userId;

    private Boolean isRead;
}
