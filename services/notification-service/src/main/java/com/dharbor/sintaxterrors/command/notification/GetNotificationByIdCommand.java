package com.dharbor.sintaxterrors.command.notification;


import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
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
public class GetNotificationByIdCommand
        extends SafeAbstractCommand<Long, NotificationResponse>
        implements PostExecutorCommand {

    private final NotificationService notificationService;

    @Override
    public void execute() {
        log.info("GetNotificationByIdCommand - Execute");
        this.output = this.notificationService.getNotificationById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetNotificationByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(NotificationExceptionConstant.FAILED_TO_GET_NOTIFICATION_MESSAGE);
        }
    }
}
