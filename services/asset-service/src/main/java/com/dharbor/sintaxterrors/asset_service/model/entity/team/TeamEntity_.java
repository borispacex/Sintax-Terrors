package com.dharbor.sintaxterrors.asset_service.model.entity.team;

import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TeamEntity.class)
public class TeamEntity_ {
    public static volatile SingularAttribute<TeamEntity, Long> id;

    public static volatile SingularAttribute<TeamEntity, String> name;

    public static volatile SingularAttribute<TeamEntity, String> description;

    public static volatile SingularAttribute<TeamEntity, Boolean> isActive;

    public static volatile SingularAttribute<TeamEntity, EmployeeEntity> projectManager;
}