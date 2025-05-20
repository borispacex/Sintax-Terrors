package com.dharbor.sintaxterrors.mapper;


import com.dharbor.sintaxterrors.dto.PaginationResponse;
import org.springframework.data.domain.Page;

public final class BaseMapper {
    public static <T, E> PaginationResponse<T> setPaginationMetaData(PaginationResponse<T> pagination, Page<E> pageItems) {
        pagination.setTotalItems(pageItems.getTotalElements());
        pagination.setCurrentPage(pageItems.getNumber() + 1);
        pagination.setPageSize(pageItems.getSize());
        pagination.setTotalPages(pageItems.getTotalPages());
        return pagination;
    }
}
