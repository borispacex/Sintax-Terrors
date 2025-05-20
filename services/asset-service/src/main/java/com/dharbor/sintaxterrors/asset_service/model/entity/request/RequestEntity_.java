package com.dharbor.sintaxterrors.asset_service.model.entity.request;

import com.dharbor.sintaxterrors.asset_service.enums.request.RequestType;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.LocalDate;

@StaticMetamodel(RequestEntity.class)
public class RequestEntity_ {
    public static volatile SingularAttribute<RequestEntity, Integer> id;
    public static volatile SingularAttribute<RequestEntity, String> title;

    public static volatile SingularAttribute<RequestEntity, String> description;

    public static volatile SingularAttribute<RequestEntity, Status> status;

    public static volatile SingularAttribute<RequestEntity, RequestType> type;
    public static volatile SingularAttribute<RequestEntity, LocalDate> createdAt;

    public static volatile SingularAttribute<RequestEntity, EmployeeEntity> employee;
}