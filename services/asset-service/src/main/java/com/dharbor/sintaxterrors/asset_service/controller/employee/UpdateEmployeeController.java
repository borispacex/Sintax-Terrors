package com.dharbor.sintaxterrors.asset_service.controller.employee;


import com.dharbor.sintaxterrors.asset_service.command.employee.UpdateEmployeeCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeeSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.UpdateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;


@Tag(name = EmployeeSwaggerDoc.TAG_NAME, description = EmployeeSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = EmployeePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class UpdateEmployeeController {

    private final UpdateEmployeeCommand updateEmployeeCommand;

    @Operation(summary = EmployeeSwaggerDoc.TAG_UPDATE_EMPLOYEE)
    @PutMapping()
    public CommonResponse<EmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest request) {
        updateEmployeeCommand.setInput(request);
        (new SafeCommandExecutor<UpdateEmployeeRequest, EmployeeResponse>()).safeExecution(updateEmployeeCommand);
        return new CommonResponse<>(updateEmployeeCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
