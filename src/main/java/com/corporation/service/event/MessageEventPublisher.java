package com.corporation.service.event;

import com.corporation.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessage(User sender, User recipient, String body, String subject) {
        MessageEvent messageEvent =
                new MessageEvent(sender, recipient, body, subject);
        applicationEventPublisher.publishEvent(messageEvent);
    }
}
