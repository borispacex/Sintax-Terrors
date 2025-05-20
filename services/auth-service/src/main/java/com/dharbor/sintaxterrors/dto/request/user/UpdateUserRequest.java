package com.dharbor.sintaxterrors.dto.request.user;

import com.dharbor.sintaxterrors.exception.constant.UserExceptionConstant;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotNull(message = UserExceptionConstant.NOT_NULL_USER_ID)
    private Integer id;

    private String password;

    private String username;

    private UserState state;

    private Integer roleId;
}
