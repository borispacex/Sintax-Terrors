package com.dharbor.sintaxterrors.asset_service.command.employee;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.EmployeeService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetEmployeeByUserIdCommand
        extends SafeAbstractCommand<Integer, EmployeeShortResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void postExecute() {
        log.info("GetEmployeeByUserIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(EmployeeExceptionConstants.FAILED_TO_GET_EMPLOYEE);
        }
    }

    @Override
    public void preExecute() {
        log.info("GetEmployeeByUserIdCommand - PreExecute");
        if (!employeeService.isIdUserRegister(this.input)) {
            throw new ProcessErrorException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public void execute() {
        log.info("GetEmployeeByUserIdCommand - Execute");
        this.output = employeeService.getShortEmployeeByUserId(this.input);
    }

}
