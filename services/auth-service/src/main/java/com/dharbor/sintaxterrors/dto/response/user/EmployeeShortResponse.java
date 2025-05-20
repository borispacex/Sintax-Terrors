package com.dharbor.sintaxterrors.dto.response.user;

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

    private Integer userId;

    private String selectedImageID;

    private String uploadedImageID;
}
