package com.dharbor.sintaxterrors.controller.role;

import com.dharbor.sintaxterrors.command.role.CreateRoleCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RolePath;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RoleSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.role.CreateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;


@Tag(name = RoleSwagger.TAG_NAME, description = RoleSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + RolePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateRoleController {

    private final CreateRoleCommand createRoleCommand;

    @PostMapping()
    @Operation(summary = RoleSwagger.TAG_DESCRIPTION_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<RoleResponse> createRole(@Valid @RequestBody CreateRoleRequest request) {
        createRoleCommand.setInput(request);
        (new SafeCommandExecutor<CreateRoleRequest, RoleResponse>()).safeExecution(createRoleCommand);
        return new CommonResponse<>(createRoleCommand.getOutput(), HttpStatus.CREATED);
    }
}