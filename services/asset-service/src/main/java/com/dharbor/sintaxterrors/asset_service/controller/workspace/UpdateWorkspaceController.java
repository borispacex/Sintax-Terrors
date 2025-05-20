package com.dharbor.sintaxterrors.asset_service.controller.workspace;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.workspace.UpdateWorkspaceCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspacePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspaceSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.UpdateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
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

@Tag(name = WorkspaceSwaggerDoc.TAG_NAME, description = WorkspaceSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = WorkspacePath.BASE_PATH)
@RequestScope
@RequiredArgsConstructor
@RestController
public class UpdateWorkspaceController {

    private final UpdateWorkspaceCommand updateWorkspaceCommand;

    @Operation(summary = WorkspaceSwaggerDoc.TAG_UPDATE_WORKSPACE)
    @PutMapping
    public CommonResponse<WorkspaceResponse> updateWorkspace(@Valid @RequestBody UpdateWorkspaceRequest request) {
        updateWorkspaceCommand.setInput(request);
        (new SafeCommandExecutor<UpdateWorkspaceRequest, WorkspaceResponse>()).safeExecution(updateWorkspaceCommand);
        return new CommonResponse<>(updateWorkspaceCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
