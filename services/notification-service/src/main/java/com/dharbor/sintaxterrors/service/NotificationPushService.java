package com.dharbor.sintaxterrors.service;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.dharbor.sintaxterrors.dto.email.NotificationMessage;

@Service
@RequiredArgsConstructor
public class NotificationPushService {

    private final SimpMessagingTemplate messagingTemplate;

    public void pushToAll(NotificationMessage message) {
        messagingTemplate.convertAndSend("/notifications/topic/notifications", message);
    }

}