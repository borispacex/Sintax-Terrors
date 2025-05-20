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

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetWorkspacesByIdListCommand
        extends SafeAbstractCommand<List<Long>, List<WorkspaceResponse>>
        implements PreExecutorCommand, PostExecutorCommand {

    private final WorkspaceService workspaceService;

    @Override
    public void execute() {
        log.info("GetWorkspacesByIdListCommand - Execute");
        this.output = workspaceService.getWorkspacesByIdList(this.input);
    }

    @Override
    public void preExecute() {
        log.info("GetWorkspacesByIdListCommand - PreExecute");
        if (this.input == null || this.input.isEmpty()) {
            throw new ProcessErrorException(WorkspaceExceptionConstants.NO_EMPTY_LIST_MESSAGE);
        }
    }

    @Override
    public void postExecute() {
        log.info("GetWorkspacesByIdListCommand - PostExecute");
        if (Utils.isNull(this.output) || this.output.isEmpty()) {
            throw new ProcessErrorException(WorkspaceExceptionConstants.FAILED_TO_GET_WORKSPACE);
        }
    }
}
