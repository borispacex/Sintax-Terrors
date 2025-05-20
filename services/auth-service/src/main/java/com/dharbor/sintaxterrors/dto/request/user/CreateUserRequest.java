package com.dharbor.sintaxterrors.dto.request.user;

import com.dharbor.sintaxterrors.exception.constant.UserExceptionConstant;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @NotEmpty(message = UserExceptionConstant.NOT_NULL_USERNAME)
    private String username;

    @NotEmpty(message = UserExceptionConstant.NOT_NULL_PASSWORD)
    private String password;

    @NotNull(message = UserExceptionConstant.NOT_NULL_ROLE_ID)
    private Integer roleId;

    @NotNull(message = UserExceptionConstant.NOT_NULL_USER_STATE)
    private UserState state;
}
