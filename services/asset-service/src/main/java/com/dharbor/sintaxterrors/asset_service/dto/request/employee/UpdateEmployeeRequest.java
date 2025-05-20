package com.dharbor.sintaxterrors.asset_service.dto.request.employee;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;

import com.dharbor.sintaxterrors.asset_service.exception.constant.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    @NotNull(message = EmployeeExceptionConstants.NOT_NULL_ID_MESSAGE)
    private Integer id;

    @NotNull(message = EmployeeExceptionConstants.NOT_NULL_CI_MESSAGE)
    private String ci;

    @NotEmpty(message = EmployeeExceptionConstants.NOT_EMPTY_FIRSTNAME_MESSAGE)
    @Pattern(regexp = RegexConstants.ONLY_LETTERS_AND_SPACES, message = EmployeeExceptionConstants.INVALID_NAME_MESSAGE)
    private String firstName;

    @Pattern(regexp = RegexConstants.ONLY_LETTERS_AND_SPACES, message = EmployeeExceptionConstants.INVALID_NAME_MESSAGE)
    private String middleName;

    @NotEmpty(message = EmployeeExceptionConstants.NOT_EMPTY_LASTNAME_MESSAGE)
    @Pattern(regexp = RegexConstants.ONLY_LETTERS_AND_SPACES, message = EmployeeExceptionConstants.INVALID_NAME_MESSAGE)
    private String lastName;

    @Pattern(regexp = RegexConstants.ONLY_LETTERS_AND_SPACES, message = EmployeeExceptionConstants.INVALID_NAME_MESSAGE)
    private String secondLastName;

    @Email(message = EmployeeExceptionConstants.INVALID_PERSONAL_EMAIL_MESSAGE)
    private String personalEmail;

    @Email(message = EmployeeExceptionConstants.INVALID_WORK_EMAIL_MESSAGE)
    private String workEmail;

    private LocalDate birthDate;

    @NotEmpty
    private String country;

    @NotNull(message = EmployeeExceptionConstants.NOT_NULL_CITY_MESSAGE)
    private BoliviaCity city;

    @Pattern(regexp = "^\\+?[\\d -]+$", message = EmployeeExceptionConstants.INVALID_PHONE_NUMBER_MESSAGE)
    private String cellphoneNumber;

    private EmployeeStatus status;

    private Integer userId;

    private Integer teamId;

    private String selectedImageID;

    private String uploadedImageID;
}
