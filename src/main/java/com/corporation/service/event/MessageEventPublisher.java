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

    public void inviteUserToTeamEvent(User sender, long userId, Team team) {
        InviteEvent inviteEvent = new InviteEvent(sender, userId, team);
        applicationEventPublisher.publishEvent(inviteEvent);
    }
}
