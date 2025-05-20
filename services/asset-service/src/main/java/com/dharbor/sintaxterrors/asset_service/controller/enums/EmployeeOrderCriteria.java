package com.dharbor.sintaxterrors.asset_service.controller.enums;


import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity_;

public enum EmployeeOrderCriteria {
    ID("ID", EmployeeEntity_.id.getName()),
    CI("CI", EmployeeEntity_.ci.getName()),
    FIRST_NAME("FIRST_NAME", EmployeeEntity_.firstName.getName()),
    MIDDLE_NAME("MIDDLE_NAME", EmployeeEntity_.middleName.getName()),
    LAST_NAME("LAST_NAME", EmployeeEntity_.lastName.getName()),
    SECOND_LAST_NAME("SECOND_LAST_NAME", EmployeeEntity_.secondLastName.getName()),
    PERSONAL_EMAIL("PERSONAL_EMAIL", EmployeeEntity_.personalEmail.getName()),
    WORK_EMAIL("WORK_EMAIL", EmployeeEntity_.workEmail.getName()),
    BIRTH_DATE("BIRTH_DATE", EmployeeEntity_.birthDate.getName()),
    COUNTRY("COUNTRY", EmployeeEntity_.country.getName()),
    CITY("CITY", EmployeeEntity_.city.getName()),
    CELL_PHONE_NUMBER("CELL_PHONE_NUMBER", EmployeeEntity_.cellphoneNumber.getName()),
    STATUS("STATUS", EmployeeEntity_.status.getName());

    private final String key;
    private final String value;

    EmployeeOrderCriteria(String key, String value) {
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
