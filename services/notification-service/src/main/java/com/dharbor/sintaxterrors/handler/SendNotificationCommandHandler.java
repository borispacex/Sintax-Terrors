package com.dharbor.sintaxterrors.handler;

import com.dharbor.sintaxterrors.command.email.CreateEmailNotificationCommand;
import com.dharbor.sintaxterrors.dto.email.EmailResponseDto;
import com.dharbor.sintaxterrors.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationCommandHandler {

    private final EmailService senderService;

    public SendNotificationCommandHandler(EmailService senderService) {
        this.senderService = senderService;
    }

    public EmailResponseDto handle(CreateEmailNotificationCommand command) {
        return senderService.sendEmail(command.getRequest());
    }
}