package com.corporation.service.event;

import com.corporation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEvent {

    private User sender;
    private User recipient;
    private String body;
    private String subject;

}
