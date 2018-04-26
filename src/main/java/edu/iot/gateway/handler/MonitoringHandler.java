package edu.iot.gateway.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import edu.iot.gateway.model.LocationStatus;
import edu.iot.gateway.service.SensorService;

@Component
public class MonitoringHandler extends TextWebSocketHandler{

	List<WebSocketSession> list = Collections.synchronizedList(new ArrayList<>());
	
	@Resource(name="sensorService")
	SensorService service;
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
		System.out.println("세션 제거");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		System.out.println("세션 추가");
		
		send(service.getLocationStatus());
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
	}
	
	public void send(Map<String, LocationStatus> locationMap) throws Exception{
		Gson gson = new Gson();
		String json = gson.toJson(locationMap);
		TextMessage message = new TextMessage(json);
		for(WebSocketSession session:list) {
			session.sendMessage(message);
		}
	}

}
