package com.inspire12.likelionwebsocket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire12.likelionwebsocket.listener.WebSocketSessionManager;
import com.inspire12.likelionwebsocket.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatAdminController {

    private final WebSocketSessionManager sessionManager;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public ChatAdminController(WebSocketSessionManager sessionManager, SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.sessionManager = sessionManager;
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/call")
    public void broadcastMessage(@RequestBody ChatMessage chatMessage) throws JsonProcessingException {
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
