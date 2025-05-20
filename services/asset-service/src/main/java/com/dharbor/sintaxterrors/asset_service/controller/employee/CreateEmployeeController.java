package com.dharbor.sintaxterrors.asset_service.controller.employee;

import com.dharbor.sintaxterrors.asset_service.command.employee.CreateEmployeeCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeeSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.CreateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;


@Tag(name = EmployeeSwaggerDoc.TAG_NAME, description = EmployeeSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = EmployeePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateEmployeeController {

    private final CreateEmployeeCommand createEmployeeCommand;

    @Operation(summary = EmployeeSwaggerDoc.TAG_CREATE_EMPLOYEE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        createEmployeeCommand.setInput(request);
        (new SafeCommandExecutor<CreateEmployeeRequest, EmployeeResponse>()).safeExecution(createEmployeeCommand);
        return new CommonResponse<>(createEmployeeCommand.getOutput(), HttpStatus.CREATED, HttpStatus.CREATED.name());
    }
}
