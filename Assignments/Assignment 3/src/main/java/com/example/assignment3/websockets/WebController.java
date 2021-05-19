package com.example.assignment3.websockets;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebController {

    @MessageMapping("/appointment")
    @SendTo("/topic/notification")
    public Notification greeting(NotificationDetails message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Notification("You have a new appointment on "
                + HtmlUtils.htmlEscape(message.getDateOfConsult()) +
                " with patient " + HtmlUtils.htmlEscape(message.getPatientName()));
    }

}
