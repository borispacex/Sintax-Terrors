package com.dharbor.sintaxterrors.dto.request.user;

import com.dharbor.sintaxterrors.controller.enums.UserOrderCriteria;
import com.dharbor.sintaxterrors.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.exception.constant.PaginationExceptionConstants;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class GetUserPageRequest {
    @Min(value = 1, message = PaginationExceptionConstants.INVALID_PAGE_MESSAGE)
    private Integer page;

    @Min(value = 1, message = PaginationExceptionConstants.INVALID_SIZE_MESSAGE)
    private Integer size;

    @Enumerated(EnumType.STRING)
    private SortDirection direction;

    @Enumerated(EnumType.STRING)
    private UserOrderCriteria order;

    private String username;

    private UserState state;

    private LocalDate dateJoined;

    private Integer roleId;
}
