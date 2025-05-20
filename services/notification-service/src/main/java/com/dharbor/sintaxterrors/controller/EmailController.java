package com.dharbor.sintaxterrors.controller;

import com.dharbor.sintaxterrors.command.email.CreateEmailNotificationCommand;
import com.dharbor.sintaxterrors.dto.email.EmailRequestDto;
import com.dharbor.sintaxterrors.dto.email.EmailResponseDto;
import com.dharbor.sintaxterrors.handler.SendNotificationCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/api/emails")
public class EmailController {

    private final SendNotificationCommandHandler handler;

    @Autowired
    public EmailController(SendNotificationCommandHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<EmailResponseDto> sendEmail(@RequestBody EmailRequestDto request) {
        CreateEmailNotificationCommand command = new CreateEmailNotificationCommand(request);
        EmailResponseDto response = handler.handle(command);
        return ResponseEntity.ok(response);
    }
}
