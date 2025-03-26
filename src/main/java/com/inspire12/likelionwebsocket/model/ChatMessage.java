package com.inspire12.likelionwebsocket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
public class ChatMessage {

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    @Getter
    private MessageType type;
    @Getter
    private String content;
    @Getter
    private String sender;

    public static ChatMessage createTestMessage(String sender, String content) {
        ChatMessage testMessage = ChatMessage.builder()
            .sender(sender)
            .content(
                String.format("""
                        %s
                        """, content))
            .type(ChatMessage.MessageType.CHAT)
            .build();

        return testMessage;
    }
}
