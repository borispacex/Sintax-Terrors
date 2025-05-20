package com.dharbor.sintaxterrors.dto.response.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    private Integer id;

    private String name;

    private String description;

    private Boolean isActive;
}
