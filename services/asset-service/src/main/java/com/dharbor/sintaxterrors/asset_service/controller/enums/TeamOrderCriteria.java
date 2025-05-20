package com.dharbor.sintaxterrors.asset_service.controller.enums;

import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity_;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeamOrderCriteria {
    ID("ID", TeamEntity_.id.getName()),
    NAME("NAME", TeamEntity_.name.getName()),
    IS_ACTIVE("IS_ACTIVE", TeamEntity_.isActive.getName());

    private final String key;
    private final String value;
}
