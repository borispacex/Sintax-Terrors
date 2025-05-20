package com.dharbor.sintaxterrors.dto.response.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmployeeResponse {
    private Integer id;

    private Integer employeeId;

    private String username;

    private String email;

    private String role;
}
