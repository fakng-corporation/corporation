package com.corporation.service.event;

import com.corporation.model.Post;
import com.corporation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEvent {
    private Post post;
    private User user;
}
