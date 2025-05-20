package com.dharbor.sintaxterrors.asset_service.command.employee;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.GetEmployeePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
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
public class GetEmployeeWithTransactionPageCommand
        extends SafeAbstractCommand<GetEmployeePageRequest, PaginationResponse<EmployeeResponse>>
        implements PostExecutorCommand {

    private final EmployeeService employeeService;

    @Override
    public void execute() {
        log.info("GetEmployeesWithTransactionPageCommand - Execute");
        this.output = employeeService.getEmployeeWithTransactionPage(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetEmployeesWithTransactionPageCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(EmployeeExceptionConstants.FAILED_TO_GET_EMPLOYEE);
        }
    }
}

