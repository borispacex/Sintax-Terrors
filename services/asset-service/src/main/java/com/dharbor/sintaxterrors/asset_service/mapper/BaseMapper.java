package com.dharbor.sintaxterrors.asset_service.mapper;


import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
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
