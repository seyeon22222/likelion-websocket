package com.inspire12.likelionwebsocket.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import com.inspire12.likelionwebsocket.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatAdminController {

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final MessageService messageService;
	@PostMapping("/call")
	public ChatMessage call(@RequestBody ChatMessage chatMessage) {
		ChatMessage testMessage = ChatMessage.createTestMessage(chatMessage.getSender(), chatMessage.getContent());
		simpMessagingTemplate.convertAndSend("/topic/public", testMessage);
		return testMessage;

	}

	@PostMapping("/call/user")
	public ChatMessage callUser(@RequestParam String userName, @RequestBody ChatMessage chatMessage) {
		messageService.callUser(userName, chatMessage);
		return chatMessage;
	}
}
