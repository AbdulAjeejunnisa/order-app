package com.abdulajeejunnisa.orderapp.webSocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatusNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public StatusNotifier(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyStatusChange(String message) {

        System.out.println("ORDER STATUS UPDATE: " + message);

        messagingTemplate.convertAndSend(
                "/topic/order-status",
                message
        );
    }
}