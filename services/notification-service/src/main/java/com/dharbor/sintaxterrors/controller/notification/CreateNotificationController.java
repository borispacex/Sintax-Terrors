package com.dharbor.sintaxterrors.controller.notification;

import com.dharbor.sintaxterrors.command.notification.CreateNotificationCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationPath;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.CreateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;


@Tag(name = NotificationSwagger.TAG_NAME, description = NotificationSwagger.TAG_DESCRIPTION)
@RequestMapping(NotificationPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateNotificationController {

    private final CreateNotificationCommand createNotificationCommand;

    @PostMapping()
    @Operation(summary = NotificationSwagger.TAG_DESCRIPTION_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<NotificationResponse> createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        createNotificationCommand.setInput(request);
        (new SafeCommandExecutor<CreateNotificationRequest, NotificationResponse>()).safeExecution(createNotificationCommand);
        return new CommonResponse<>(createNotificationCommand.getOutput(), HttpStatus.CREATED);
    }
}