package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> findPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

}
