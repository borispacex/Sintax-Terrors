package com.dharbor.sintaxterrors.asset_service.model.entity.employee;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.LocalDate;

@StaticMetamodel(EmployeeEntity.class)
public class EmployeeEntity_ {
    public static volatile SingularAttribute<EmployeeEntity, Long> id;

    public static volatile SingularAttribute<EmployeeEntity, String> ci;

    public static volatile SingularAttribute<EmployeeEntity, String> firstName;

    public static volatile SingularAttribute<EmployeeEntity, String> middleName;

    public static volatile SingularAttribute<EmployeeEntity, String> lastName;

    public static volatile SingularAttribute<EmployeeEntity, String> secondLastName;

    public static volatile SingularAttribute<EmployeeEntity, String> personalEmail;

    public static volatile SingularAttribute<EmployeeEntity, String> workEmail;

    public static volatile SingularAttribute<EmployeeEntity, LocalDate> birthDate;

    public static volatile SingularAttribute<EmployeeEntity, String> country;

    public static volatile SingularAttribute<EmployeeEntity, BoliviaCity> city;

    public static volatile SingularAttribute<EmployeeEntity, String> cellphoneNumber;

    public static volatile SingularAttribute<EmployeeEntity, EmployeeStatus> status;

    public static volatile SingularAttribute<EmployeeEntity, Integer> userId;

    public static volatile SingularAttribute<EmployeeEntity, TeamEntity> team;

    public static volatile SingularAttribute<EmployeeEntity, String> selectedImageID;

    public static volatile SingularAttribute<EmployeeEntity, String> uploadedImageID;
}