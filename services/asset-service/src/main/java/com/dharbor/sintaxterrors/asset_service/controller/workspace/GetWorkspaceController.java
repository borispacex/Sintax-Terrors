package com.dharbor.sintaxterrors.asset_service.controller.workspace;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.workspace.GetWorkspaceByIdCommand;
import com.dharbor.sintaxterrors.asset_service.command.workspace.GetWorkspacePageCommand;
import com.dharbor.sintaxterrors.asset_service.command.workspace.GetWorkspacesByIdListCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspacePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.WorkspaceControllerConstants.WorkspaceSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.controller.enums.WorkspaceOrderCriteria;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.GetWorkspacePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Tag(name = WorkspaceSwaggerDoc.TAG_NAME, description = WorkspaceSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = WorkspacePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetWorkspaceController {

    private final GetWorkspaceByIdCommand getWorkspaceByIdCommand;
    private final GetWorkspacePageCommand getWorkspacePageCommand;
    private final GetWorkspacesByIdListCommand getWorkspacesByIdListCommand;

    @Operation(summary = WorkspaceSwaggerDoc.TAG_GET_WORKSPACE_BY_ID)
    @GetMapping(value = "/{id}")
    public CommonResponse<WorkspaceResponse> getWorkspaceById(@PathVariable Long id) {
        getWorkspaceByIdCommand.setInput(id);
        (new SafeCommandExecutor<Long, WorkspaceResponse>()).safeExecution(getWorkspaceByIdCommand);
        return new CommonResponse<>(getWorkspaceByIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @GetMapping
    @Operation(summary = WorkspaceSwaggerDoc.TAG_GET_WORKSPACES_BY_PAGE)
    public CommonPaginationResponse<WorkspaceResponse> getWorkspacePage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) WorkspaceOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) BoliviaCity city
    ) {
        GetWorkspacePageRequest request = new GetWorkspacePageRequest(
                page,
                size,
                (!Utils.isNull(order)) ? order.getValue() : null,
                direction,
                search,
                city
        );
        getWorkspacePageCommand.setInput(request);
        (new SafeCommandExecutor<GetWorkspacePageRequest, PaginationResponse<WorkspaceResponse>>()).safeExecution(getWorkspacePageCommand);
        return new CommonPaginationResponse<>(getWorkspacePageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @PostMapping(WorkspacePath.BY_ID_LIST)
    @Operation(summary = WorkspaceSwaggerDoc.TAG_GET_WORKSPACES_BY_LIST)
    public CommonResponse<List<WorkspaceResponse>> getWorkspacesByIdList(@RequestBody List<Long> idList) {
        getWorkspacesByIdListCommand.setInput(idList);
        (new SafeCommandExecutor<List<Long>, List<WorkspaceResponse>>()).safeExecution(getWorkspacesByIdListCommand);
        return new CommonResponse<>(getWorkspacesByIdListCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
