package com.webSocket.demo.webSocket.controller;

import com.webSocket.demo.webSocket.model.Patient;
import com.webSocket.demo.webSocket.model.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Patient patient) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("there is new detection, " + patient.toString());

    }


}
