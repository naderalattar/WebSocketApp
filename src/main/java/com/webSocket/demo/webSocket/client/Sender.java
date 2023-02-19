package com.webSocket.demo.webSocket.client;

import com.webSocket.demo.webSocket.model.Greeting;
import com.webSocket.demo.webSocket.model.Patient;
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

public class Sender {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient client=new StandardWebSocketClient();
        WebSocketStompClient stompClient=new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ClientOneSessionHandler clientOneSessionHandler=new ClientOneSessionHandler();
        ListenableFuture<StompSession> sessionAsync=
                stompClient.connect("ws://localhost:8080//weetingList",clientOneSessionHandler);
        StompSession session=sessionAsync.get();
//        session.subscribe("/topic/greetings",clientOneSessionHandler);
        while (true){
            Thread.sleep(3000);
            session.send("/app/hello", new Patient("nader","0102489284"));
        }


    }
}
class ClientOneSessionHandler extends StompSessionHandlerAdapter{
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println(((Greeting)payload).getContent());


    }
}
