package com.dharbor.sintaxterrors.util.function;

import com.dharbor.sintaxterrors.enums.shared.SortDirection;
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
}
