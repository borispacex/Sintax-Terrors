package com.dharbor.sintaxterrors.command.email;


import com.dharbor.sintaxterrors.dto.email.EmailRequestDto;

public class CreateEmailNotificationCommand {
    private final EmailRequestDto request;

    public CreateEmailNotificationCommand(EmailRequestDto request) {
        this.request = request;
    }

    public EmailRequestDto getRequest() {
        return request;
    }
}