package com.dharbor.sintaxterrors.asset_service.command.workspace;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.service.WorkspaceService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class DeleteWorkspaceCommand
        extends SafeAbstractCommand<Long, WorkspaceResponse> {

    private final WorkspaceService workspaceService;

    @Override
    public void execute() {
        log.info("DeleteWorkspaceCommand - Execute");
        this.output = workspaceService.deleteWorkspace(this.input);
    }

}