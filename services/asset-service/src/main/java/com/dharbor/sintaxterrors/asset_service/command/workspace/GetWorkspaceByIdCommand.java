package com.dharbor.sintaxterrors.asset_service.command.workspace;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.WorkspaceExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.WorkspaceService;
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
public class GetWorkspaceByIdCommand
        extends SafeAbstractCommand<Long, WorkspaceResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final WorkspaceService workspaceService;

    @Override
    public void preExecute() {
        log.info("GetWorkspaceByIdCommand - PreExecute");
        if (!workspaceService.isWorkspaceIdExist(this.input)) {
            throw new ProcessErrorException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND);
        }
    }

    @Override
    public void execute() {
        log.info("GetWorkspaceByIdCommand - Execute");
        this.output = workspaceService.getWorkspaceResponseById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetWorkspaceByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(WorkspaceExceptionConstants.FAILED_TO_GET_WORKSPACE);
        }
    }
}
