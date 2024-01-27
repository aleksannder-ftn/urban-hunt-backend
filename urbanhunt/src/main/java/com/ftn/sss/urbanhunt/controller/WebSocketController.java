package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Notification;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final RealEstateService realEstateService;
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template, RealEstateService realEstateService) {
        this.template = template;
        this.realEstateService = realEstateService;
    }

    @MessageMapping("/sendAgentNotification/{realEstateId}")
    public void sendAgentNotification(@DestinationVariable Long realEstateId, Notification notification) {
        Agent agent = realEstateService.findAgentById(realEstateId);
        template.convertAndSend("/topic/agent/notification/" + agent.getId(), notification);
    }

}
