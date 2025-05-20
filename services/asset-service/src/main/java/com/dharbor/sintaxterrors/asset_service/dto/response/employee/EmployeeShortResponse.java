package com.dharbor.sintaxterrors.asset_service.dto.response.employee;

import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeShortResponse {

    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String secondLastName;

    private String personalEmail;

    private String workEmail;

    private String cellphoneNumber;

    private EmployeeStatus status;

    private Integer userId;

    private String selectedImageID;

    private String uploadedImageID;
}
