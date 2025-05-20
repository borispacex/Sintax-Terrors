package com.dharbor.sintaxterrors.dto;

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
