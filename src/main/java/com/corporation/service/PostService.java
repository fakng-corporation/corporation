package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepositroy postRepositroy;

    public Post save(Post post) {
        return postRepositroy.save(post);
    }

}
