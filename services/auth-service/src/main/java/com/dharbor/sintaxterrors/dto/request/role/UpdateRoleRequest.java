package com.dharbor.sintaxterrors.dto.request.role;


import com.dharbor.sintaxterrors.exception.constant.RoleExceptionConstant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateRoleRequest {
    @NotNull(message = RoleExceptionConstant.NOT_NULL_ID)
    private Integer id;

    @NotEmpty(message = RoleExceptionConstant.NOT_NULL_ROLE_NAME)
    private String name;

    private String description;

    private Boolean isActive;
}
