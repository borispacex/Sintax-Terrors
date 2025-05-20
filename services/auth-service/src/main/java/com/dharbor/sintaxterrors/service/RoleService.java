package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.role.CreateRoleRequest;
import com.dharbor.sintaxterrors.dto.request.role.GetRolePageRequest;
import com.dharbor.sintaxterrors.dto.request.role.UpdateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;

public interface RoleService {
    RoleResponse saveRole(CreateRoleRequest input);

    void validateCreateRoleRequest(CreateRoleRequest input);

    RoleResponse updateRole(UpdateRoleRequest input);

    void validateUpdateRoleRequest(UpdateRoleRequest input);

    RoleResponse getRoleById(Integer input);

    PaginationResponse<RoleResponse> getRolePage(GetRolePageRequest input);
}