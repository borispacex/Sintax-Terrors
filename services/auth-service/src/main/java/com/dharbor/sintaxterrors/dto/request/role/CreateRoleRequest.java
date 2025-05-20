package com.dharbor.sintaxterrors.dto.request.role;


import com.dharbor.sintaxterrors.exception.constant.RoleExceptionConstant;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleRequest
{
    @NotEmpty(message = RoleExceptionConstant.NOT_NULL_ROLE_NAME)
    private String name;

    private String description;

    private Boolean isActive = true;
}
