package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post savePostDraft(Post post) {
        post.setPublished(false);
        return postRepository.save(post);
    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
