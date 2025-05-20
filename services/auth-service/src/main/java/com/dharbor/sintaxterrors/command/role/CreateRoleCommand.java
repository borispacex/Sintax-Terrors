package com.dharbor.sintaxterrors.command.role;


import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.dto.request.role.CreateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.RoleExceptionConstant;
import com.dharbor.sintaxterrors.service.RoleService;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class CreateRoleCommand
        extends SafeAbstractCommand<CreateRoleRequest, RoleResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final RoleService roleService;

    @Override
    public void preExecute() {
        log.info("CreateRoleCommand - PreExecute");
        roleService.validateCreateRoleRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("CreateRoleCommand - Execute");
        this.output = this.roleService.saveRole(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateRoleCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(RoleExceptionConstant.FAILED_TO_CREATE_ROLE_MESSAGE);
        }
    }
}