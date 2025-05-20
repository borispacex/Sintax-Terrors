package com.dharbor.sintaxterrors.asset_service.model.entity.workspace;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkspaceEntity.class)
public class WorkspaceEntity_ {

    public static volatile SingularAttribute<WorkspaceEntity, Long> id;

    public static volatile SingularAttribute<WorkspaceEntity, BoliviaCity> city;

    public static volatile SingularAttribute<WorkspaceEntity, String> location;

}
