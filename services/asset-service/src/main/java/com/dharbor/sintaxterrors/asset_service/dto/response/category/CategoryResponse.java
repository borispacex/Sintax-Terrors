package com.dharbor.sintaxterrors.asset_service.dto.response.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long id;

    private String name;

    private String description;

    private Integer usefulLifeMonths;

    private Boolean isDepreciable;
}
