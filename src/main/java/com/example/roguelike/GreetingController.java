package com.example.roguelike;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        if (message.getName().startsWith("/dice")) {
            int min = 0;
            int max = 100;
            int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
            return new Greeting(HtmlUtils.htmlEscape("dice result : " + randomWithMathRandom));
        }
        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
    }

}