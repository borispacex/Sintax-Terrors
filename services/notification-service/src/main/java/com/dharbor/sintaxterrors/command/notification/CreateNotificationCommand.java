package com.dharbor.sintaxterrors.command.notification;


import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.dto.request.CreateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.NotificationExceptionConstant;
import com.dharbor.sintaxterrors.service.NotificationService;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class CreateNotificationCommand
        extends SafeAbstractCommand<CreateNotificationRequest, NotificationResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final NotificationService notificationService;

    @Override
    public void preExecute() {
        log.info("CreateNotificationCommand - PreExecute");
        notificationService.validateCreateNotificationRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("CreateNotificationCommand - Execute");
        this.output = this.notificationService.saveNotification(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateNotificationCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(NotificationExceptionConstant.FAILED_TO_CREATE_NOTIFICATION_MESSAGE);
        }
    }
}