package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.employee.CreateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.GetEmployeePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.UpdateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse saveEmployee(CreateEmployeeRequest employeeRequest);

    boolean isEmployeeCiExist(String employeeCi);

    EmployeeResponse updateEmployee(UpdateEmployeeRequest employee);

    boolean isEmployeeIdExist(Integer id);

    EmployeeResponse getEmployeeResponseById(Integer id);

    EmployeeEntity getEmployeeEntityById(Integer id);

    EmployeeResponse getEmployeeByCi(String employeeCi);

    void validateCreateEmployeeRequest(CreateEmployeeRequest request);

    EmployeeShortResponse getShortEmployeeByUserId(Integer userId);

    void validateUpdateEmployeeRequest(UpdateEmployeeRequest request);

    PaginationResponse<EmployeeResponse> getEmployeePage(GetEmployeePageRequest request);

    PaginationResponse<EmployeeResponse> getEmployeeWithTransactionPage(GetEmployeePageRequest request);

    List<EmployeeResponse> getEmployeesByIdList(List<Integer> input);

    EmployeeResponse getEmployeesByIdUser(Integer input);

    boolean isIdUserRegister(Integer id);
}
