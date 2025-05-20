package com.dharbor.sintaxterrors.asset_service.dto.response.employee;

import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponse {

    private Integer id;

    private String ci;

    private String firstName;

    private String middleName;

    private String lastName;

    private String secondLastName;

    private String personalEmail;

    private String workEmail;

    private LocalDate birthDate;

    private String country;

    private BoliviaCity city;

    private String cellphoneNumber;

    private EmployeeStatus status;

    private TeamResponse team;

    private Integer userId;

    private String selectedImageID;

    private String uploadedImageID;
}
