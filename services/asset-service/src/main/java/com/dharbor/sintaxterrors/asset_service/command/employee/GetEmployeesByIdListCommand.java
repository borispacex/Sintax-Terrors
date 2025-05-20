package com.dharbor.sintaxterrors.asset_service.command.employee;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.EmployeeService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetEmployeesByIdListCommand
        extends SafeAbstractCommand<List<Integer>, List<EmployeeResponse>>
        implements PreExecutorCommand, PostExecutorCommand {

    private final EmployeeService employeeService;

    @Override
    public void execute() {
        log.info("GetEmployeesByIdListCommand - Execute");
        this.output = employeeService.getEmployeesByIdList(this.input);
    }

    @Override
    public void preExecute() {
        log.info("GetEmployeesByIdListCommand - PreExecute");
        if (this.input.isEmpty()) {
            throw new ProcessErrorException(EmployeeExceptionConstants.NO_EMPTY_LIST_MESSAGE);
        }

    }

    @Override
    public void postExecute() {
        log.info("GetEmployeesByIdListCommand - PostExecute");
        if (this.output.isEmpty()) {
            throw new ProcessErrorException(EmployeeExceptionConstants.FAILED_TO_GET_EMPLOYEE);
        }
    }
}