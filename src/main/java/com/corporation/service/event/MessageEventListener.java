package com.corporation.service.event;

import com.corporation.model.service.Message;
import com.corporation.service.MessageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageEventListener {

    private MessageService messageService;

    @EventListener
    @Transactional
    public void inviteMessageEventListener(MessageEvent messageEvent) {
        Message message = Message.builder()
                .body(messageEvent.getBody())
                .sender(messageEvent.getSender())
                .recipient(messageEvent.getRecipient())
                .subject(messageEvent.getSubject())
                .build();
        messageService.save(message);
    }
}
