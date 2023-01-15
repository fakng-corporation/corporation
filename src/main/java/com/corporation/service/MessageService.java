package com.corporation.service;

import com.corporation.model.service.InviteToTeam;
import com.corporation.model.service.Message;
import com.corporation.repository.service.InviteToTeamRepository;
import com.corporation.repository.service.MessageRepository;
import com.corporation.service.event.InviteEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final InviteToTeamRepository inviteToTeamRepository;

    @EventListener
    @Transactional
    public void inviteMessageEventListener(InviteEvent inviteEvent) {
        String code = UUID.randomUUID().toString();
        String subject = "Invite to Team";
        String body = String.format(
                "You were invited to team %s %s. Use code: %s",
                inviteEvent.getTeam().getTitle(),
                inviteEvent.getTeam().getDescription(),
                code);
        Message message = Message.builder()
                .body(body)
                .sender(inviteEvent.getSender())
                .subject(subject)
                .build();
        save(message);

        InviteToTeam inviteToTeam = InviteToTeam.builder()
                .senderId(inviteEvent.getSender().getId())
                .recipientId(inviteEvent.getUserId())
                .teamId(inviteEvent.getTeam().getId())
                .code(code)
                .build();
        inviteToTeamRepository.save(inviteToTeam);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
