package com.corporation.service;

import com.corporation.model.service.Message;
import com.corporation.repository.service.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
