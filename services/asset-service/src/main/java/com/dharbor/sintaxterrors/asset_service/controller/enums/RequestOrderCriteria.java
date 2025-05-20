package com.dharbor.sintaxterrors.asset_service.controller.enums;


import com.dharbor.sintaxterrors.asset_service.model.entity.request.RequestEntity_;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestOrderCriteria {
    ID("ID", RequestEntity_.id.getName()),
    TITLE("TITLE", RequestEntity_.title.getName()),
    STATUS("STATUS", RequestEntity_.status.getName()),
    TYPE("TYPE", RequestEntity_.type.getName()),
    CREATED_AT("CREATED_AT", RequestEntity_.createdAt.getName()),
    EMPLOYEE_ID("EMPLOYEE_ID", RequestEntity_.employee.getName());

    private final String key;
    private final String value;
    }
