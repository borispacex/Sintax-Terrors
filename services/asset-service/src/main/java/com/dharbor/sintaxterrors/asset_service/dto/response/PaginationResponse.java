package com.dharbor.sintaxterrors.asset_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> {
    private List<T> items;

    private Long totalItems;

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalPages;
}
