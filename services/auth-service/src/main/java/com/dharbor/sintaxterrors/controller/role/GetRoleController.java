package com.dharbor.sintaxterrors.controller.role;


import com.dharbor.sintaxterrors.command.role.GetRoleByIdCommand;
import com.dharbor.sintaxterrors.command.role.GetRolePageCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RolePath;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RoleSwagger;
import com.dharbor.sintaxterrors.controller.enums.RoleOrderCriteria;
import com.dharbor.sintaxterrors.dto.CommonPaginationResponse;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.role.GetRolePageRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import com.dharbor.sintaxterrors.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.util.constant.FilterConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = RoleSwagger.TAG_NAME, description = RoleSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + RolePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetRoleController {

    private final GetRoleByIdCommand getRoleByIdCommand;
    private final GetRolePageCommand getRolePageCommand;

    @GetMapping("/{id}")
    @Operation(summary = RoleSwagger.TAG_DESCRIPTION_ID)
    public CommonResponse<RoleResponse> getRoleById(@PathVariable Integer id) {
        getRoleByIdCommand.setInput(id);
        (new SafeCommandExecutor<Integer, RoleResponse>()).safeExecution(getRoleByIdCommand);
        return new CommonResponse<>(getRoleByIdCommand.getOutput());
    }

    @GetMapping()
    @Operation(summary = RoleSwagger.TAG_DESCRIPTION_PAGE)
    public CommonPaginationResponse<RoleResponse> getRoleListByPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_SIZE) Integer size,
            @RequestParam(required = false) RoleOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean isActive) {
        GetRolePageRequest request = GetRolePageRequest.builder()
                .page(page)
                .size(size)
                .order(!Utils.isNull(order) ? order.getValue() : null)
                .direction(direction)
                .name(name)
                .isActive(isActive)
                .build();
        getRolePageCommand.setInput(request);
        (new SafeCommandExecutor<GetRolePageRequest, PaginationResponse<RoleResponse>>()).safeExecution(getRolePageCommand);
        return new CommonPaginationResponse<>(getRolePageCommand.getOutput());
    }
}
