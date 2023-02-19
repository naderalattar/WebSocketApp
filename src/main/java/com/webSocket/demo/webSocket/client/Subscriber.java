package com.webSocket.demo.webSocket.client;

import com.webSocket.demo.webSocket.model.Greeting;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class Subscriber {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient client=new StandardWebSocketClient();
        WebSocketStompClient stompClient=new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ClientOneSessionHandler2 clientOneSessionHandler=new ClientOneSessionHandler2();
        ListenableFuture<StompSession> sessionAsync=
                stompClient.connect("ws://localhost:8080//weetingList",clientOneSessionHandler);
        StompSession session=sessionAsync.get();
        session.subscribe("/topic/greetings", clientOneSessionHandler);
        while (true) {

        }
    }

}
class ClientOneSessionHandler2 extends StompSessionHandlerAdapter {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println(((Greeting)payload).getContent());


    }
}
