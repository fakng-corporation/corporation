package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post savePostDraft(Post post) {
        post.setIsPublished(false);
        return postRepository.save(post);
    }
}
