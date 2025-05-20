package com.dharbor.sintaxterrors.asset_service.dto.request.category;

import com.dharbor.sintaxterrors.asset_service.exception.constant.CategoryExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.model.constant.CategoryEntityConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {
    @NotNull(message = CategoryExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Long id;

    @NotEmpty(message = CategoryExceptionConstants.NOT_EMPTY_NAME_MESSAGE)
    @Size(max = CategoryEntityConstants.CategoryTable.Name.LENGTH,
            message = CategoryExceptionConstants.MAX_LENGTH_NAME_MESSAGE)
    private String name;

    private String description;

    @Min(value = 0, message = CategoryExceptionConstants.MIN_USEFUL_LIFE_MESSAGE)
    private Integer usefulLifeMonths;

    @NotNull(message = CategoryExceptionConstants.NOT_NULL_DEPRECIABLE_MESSAGE)
    private Boolean isDepreciable;
}