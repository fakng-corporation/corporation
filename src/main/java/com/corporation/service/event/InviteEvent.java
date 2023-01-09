package com.corporation.service.event;

import com.corporation.model.Team;

import com.corporation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InviteEvent {

    private User sender;
    private long userId;
    private Team team;

}
