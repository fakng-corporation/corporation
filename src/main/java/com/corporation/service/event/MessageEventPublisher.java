package com.corporation.service.event;

import com.corporation.model.Team;
import com.corporation.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public void inviteUserToTeamEvent(User sender, User recipient, Team team) {
        InviteEvent inviteEvent = new InviteEvent(sender, recipient, team);
        applicationEventPublisher.publishEvent(inviteEvent);
    }
}
