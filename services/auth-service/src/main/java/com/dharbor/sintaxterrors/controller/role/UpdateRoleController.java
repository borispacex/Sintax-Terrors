package com.dharbor.sintaxterrors.controller.role;

import com.dharbor.sintaxterrors.command.role.UpdateRoleCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RolePath;
import com.dharbor.sintaxterrors.controller.constant.RoleControllerConstant.RoleSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.role.UpdateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
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


@Tag(name = RoleSwagger.TAG_NAME, description = RoleSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + RolePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class UpdateRoleController {

    private final UpdateRoleCommand updateRoleCommand;

    @PutMapping()
    @Operation(summary = RoleSwagger.TAG_DESCRIPTION_UPDATE)
    public CommonResponse<RoleResponse> updateRole(@Valid @RequestBody UpdateRoleRequest request) {
        updateRoleCommand.setInput(request);
        (new SafeCommandExecutor<UpdateRoleRequest, RoleResponse>()).safeExecution(updateRoleCommand);
        return new CommonResponse<>(updateRoleCommand.getOutput(), HttpStatus.OK);
    }
}