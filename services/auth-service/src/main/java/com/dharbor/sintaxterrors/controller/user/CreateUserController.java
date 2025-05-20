package com.dharbor.sintaxterrors.controller.user;

import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.command.user.CreateUserCommand;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserPath;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.user.CreateUserRequest;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = UserSwagger.TAG_NAME, description = UserSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + UserPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateUserController {

    private final CreateUserCommand createUserCommand;

    @PostMapping()
    @Operation(summary = UserSwagger.TAG_DESCRIPTION_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        createUserCommand.setInput(request);
        (new SafeCommandExecutor<CreateUserRequest, UserResponse>()).safeExecution(createUserCommand);
        return new CommonResponse<>(createUserCommand.getOutput(), HttpStatus.CREATED);
    }
}
