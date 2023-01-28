package com.corporation.service;

import com.corporation.dto.PostDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Post savePostDraft(Post post) {
        post.setPublished(false);
        return postRepository.save(post);
    }

    @Transactional
    public Page<PostDto> getUserPostsById(long userId, int page, int pageSize) {
        return postRepository.getUserPostsById(userId, PageRequest.of(page, pageSize))
                .map(postMapper::toDto);
    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Post updatePost(PostDto postDto) {
        Post updatedPost = findById(postDto.getId());
        postMapper.updateFromDto(postDto, updatedPost);
        return postRepository.save(updatedPost);
    }

    @Transactional
    public Post findById(long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundEntityException(
                        String.format("Post %d does not exist", postId)));
    }
}
