package com.dharbor.sintaxterrors.asset_service.command.employee;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.UpdateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.EmployeeService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class UpdateEmployeeCommand extends SafeAbstractCommand<UpdateEmployeeRequest, EmployeeResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final EmployeeService employeeService;

    @Override
    public void execute() {
        log.info("UpdateEmployeeCommand - Execute");
        this.output = employeeService.updateEmployee(input);
    }

    @Override
    public void postExecute() {
        log.info("UpdateEmployeeCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(EmployeeExceptionConstants.FAILED_TO_UPDATE_EMPLOYEE);
        }
    }

    @Override
    public void preExecute() {
        log.info("UpdateEmployeeCommand - PreExecute");
        employeeService.validateUpdateEmployeeRequest(input);
    }
}
