package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/sendNotification/{id}")
    public void sendNotification(@DestinationVariable Long id, Notification notification) {
        template.convertAndSend("/topic/notification/" + id, notification);
    }

}
