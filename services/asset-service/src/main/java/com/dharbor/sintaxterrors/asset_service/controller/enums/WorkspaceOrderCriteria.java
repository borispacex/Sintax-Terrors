package com.dharbor.sintaxterrors.asset_service.controller.enums;

import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity_;

public enum WorkspaceOrderCriteria {

    ID("ID",WorkspaceEntity_.id.getName()),
    CITY("CITY", WorkspaceEntity_.city.getName()),
    LOCATION("LOCATION", WorkspaceEntity_.location.getName());

    private final String key;
    private final String value;

    WorkspaceOrderCriteria(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
