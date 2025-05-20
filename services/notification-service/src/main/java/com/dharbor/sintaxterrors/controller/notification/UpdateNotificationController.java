package com.dharbor.sintaxterrors.controller.notification;

import com.dharbor.sintaxterrors.command.notification.UpdateNotificationCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationPath;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationSwagger;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.request.UpdateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
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


@Tag(name = NotificationSwagger.TAG_NAME, description = NotificationSwagger.TAG_DESCRIPTION)
@RequestMapping(value = NotificationPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class UpdateNotificationController {

    private final UpdateNotificationCommand updateNotificationCommand;

    @PutMapping()
    @Operation(summary = NotificationSwagger.TAG_DESCRIPTION_UPDATE)
    public CommonResponse<NotificationResponse> updateRole(@Valid @RequestBody UpdateNotificationRequest request) {
        updateNotificationCommand.setInput(request);
        (new SafeCommandExecutor<UpdateNotificationRequest, NotificationResponse>()).safeExecution(updateNotificationCommand);
        return new CommonResponse<>(updateNotificationCommand.getOutput(), HttpStatus.OK);
    }
}