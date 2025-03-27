package com.inspire12.likelionwebsocket.service;

import com.inspire12.likelionwebsocket.model.ChatMessage;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

	private final SimpMessagingTemplate simpMessagingTemplate;

	public ChatMessage createWelcomeMessage(ChatMessage chatMessage) {
		ChatMessage welcomeMessage = ChatMessage.builder().sender("System").content(String.format("""
			%s 님이 들어왔습니다.
			""", chatMessage.getSender())).type(ChatMessage.MessageType.JOIN).build();

		return welcomeMessage;
	}

	public ChatMessage callUser(String userName, ChatMessage chatMessage) {
		ChatMessage sendMessage = ChatMessage.builder()
			.type(chatMessage.getType())
			.content("귓속말이야. :" + chatMessage.getContent())
			.sender(chatMessage.getSender())
			.build();
		log.info("Sending private message to user: {}", userName);
		simpMessagingTemplate.convertAndSendToUser(userName, "/queue/private", chatMessage);
		return chatMessage;
	}
}
