package com.dharbor.sintaxterrors.controller;


import com.dharbor.sintaxterrors.dto.email.NotificationMessage;
import com.dharbor.sintaxterrors.service.NotificationPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications/api/ws")
public class NotificationSocketController {

    private final NotificationPushService pushService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> getWebSocketInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("websocketEndpoint", "/notifications/ws");
        info.put("subscriptionTopic", "/notifications/topic/notifications");
        info.put("description", "Conéctate vía STOMP al endpoint WebSocket y suscríbete al topic para recibir notificaciones en tiempo real.");
        return ResponseEntity.ok(info);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationMessage message) {
        pushService.pushToAll(message);
        return ResponseEntity.ok("Notificación enviada correctamente vía WebSocket.");
    }
}