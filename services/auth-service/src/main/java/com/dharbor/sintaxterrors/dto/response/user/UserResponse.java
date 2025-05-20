package com.dharbor.sintaxterrors.dto.response.user;


import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserResponse {
    private Integer id;

    private String username;

    private UserState state;

    private String roleName;

    private Integer roleId;

    private LocalDateTime dateJoined;
}
