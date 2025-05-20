package com.dharbor.sintaxterrors.controller.enums;


import com.dharbor.sintaxterrors.model.entity.role.RoleEntity_;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleOrderCriteria {

    ID("ID", RoleEntity_.id.getName()),
    NAME("NAME", RoleEntity_.name.getName()),
    IS_ACTIVE("IS_ACTIVE", RoleEntity_.isActive.getName());

    private final String key;

    private final String value;

}
