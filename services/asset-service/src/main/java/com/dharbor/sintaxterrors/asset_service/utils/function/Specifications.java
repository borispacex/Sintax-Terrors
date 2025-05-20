package com.dharbor.sintaxterrors.asset_service.utils.function;


import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import org.springframework.data.domain.Sort;

public final class Specifications {
    public static Sort buildSorting(String order, SortDirection direction) {
        Sort sort;
        if (!Utils.isNull(order)) {
            sort = direction.equals(SortDirection.ASC) ? Sort.by(order).ascending() : Sort.by(order).descending();
        } else {
            sort = Sort.unsorted();
        }
        return sort;
    }

    public static Sort buildSortingWorkspaces(String order, SortDirection direction) {
        if (Utils.isNull(order)) {
            return Sort.unsorted();
        }

        if ("city".equalsIgnoreCase(order)) {
            order = "cityName";
        }

        return direction.equals(SortDirection.ASC)
                ? Sort.by(order).ascending()
                : Sort.by(order).descending();
    }
}
