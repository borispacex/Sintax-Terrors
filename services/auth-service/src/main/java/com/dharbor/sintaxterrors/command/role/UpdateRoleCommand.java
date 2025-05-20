package com.dharbor.sintaxterrors.command.role;

import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.dto.request.role.UpdateRoleRequest;
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
public class UpdateRoleCommand
        extends SafeAbstractCommand<UpdateRoleRequest, RoleResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final RoleService roleService;

    @Override
    public void preExecute() {
        log.info("UpdateRoleCommand - PreExecute");
        roleService.validateUpdateRoleRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("UpdateRoleCommand - Execute");
        this.output = roleService.updateRole(this.input);
    }

    @Override
    public void postExecute() {
        log.info("UpdateRoleCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(RoleExceptionConstant.FAILED_TO_UPDATE_ROLE_MESSAGE);
        }
    }
}