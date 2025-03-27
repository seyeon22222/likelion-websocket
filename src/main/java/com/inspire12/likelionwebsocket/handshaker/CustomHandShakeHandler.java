package com.inspire12.likelionwebsocket.handshaker;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
public class CustomHandShakeHandler extends DefaultHandshakeHandler {

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
		Map<String, Object> attributes) {
		String userName = getUserNameFromRequest(request);
		return new Principal() {
			@Override
			public String getName() {
				return userName;
			}
		};
	}


	private String getUserNameFromRequest(ServerHttpRequest request) {
		String query = request.getURI().getQuery();
		if (query != null && query.contains("token=")) {
			return query.split("token=")[1];
		}
		return null;
	}
}
