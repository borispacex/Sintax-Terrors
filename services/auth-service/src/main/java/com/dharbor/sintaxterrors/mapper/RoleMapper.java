package com.dharbor.sintaxterrors.mapper;

import com.dharbor.sintaxterrors.dto.request.role.CreateRoleRequest;
import com.dharbor.sintaxterrors.dto.request.role.UpdateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    RoleEntity mapperToRoleEntityFrom(CreateRoleRequest source);

    RoleResponse mapperToRoleResponseFrom(RoleEntity source);

    void updateRoleEntityFrom(UpdateRoleRequest source, @MappingTarget RoleEntity target);
}