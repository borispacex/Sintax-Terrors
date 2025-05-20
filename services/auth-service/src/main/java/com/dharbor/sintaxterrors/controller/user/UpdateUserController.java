package com.dharbor.sintaxterrors.controller.user;

import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.command.user.UpdateUserCommand;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserPath;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.user.UpdateUserRequest;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
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

@Tag(name = UserSwagger.TAG_NAME, description = UserSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + UserPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserCommand updateUserCommand;

    @PutMapping()
    @Operation(summary = UserSwagger.TAG_DESCRIPTION_UPDATE)
    public CommonResponse<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        updateUserCommand.setInput(request);
        (new SafeCommandExecutor<UpdateUserRequest, UserResponse>()).safeExecution(updateUserCommand);
        return new CommonResponse<>(updateUserCommand.getOutput(), HttpStatus.OK);
    }
}
