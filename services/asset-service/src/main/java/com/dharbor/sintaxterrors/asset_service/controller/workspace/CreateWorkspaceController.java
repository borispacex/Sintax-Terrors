package com.dharbor.sintaxterrors.asset_service.controller.workspace;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.workspace.CreateWorkspaceCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspacePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspaceSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.CreateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = WorkspaceSwaggerDoc.TAG_NAME, description = WorkspaceSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = WorkspacePath.BASE_PATH)
@RequestScope
@RequiredArgsConstructor
@RestController
public class CreateWorkspaceController {

    private final CreateWorkspaceCommand createWorkspaceCommand;

    @Operation(summary = WorkspaceSwaggerDoc.TAG_CREATE_WORKSPACE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<WorkspaceResponse> createWorkspace(@Valid @RequestBody CreateWorkspaceRequest request) {
        createWorkspaceCommand.setInput(request);
        new SafeCommandExecutor<CreateWorkspaceRequest, WorkspaceResponse>().safeExecution(createWorkspaceCommand);
        return new CommonResponse<>(
                createWorkspaceCommand.getOutput(),
                HttpStatus.CREATED,
                HttpStatus.CREATED.name()
        );
    }
}
