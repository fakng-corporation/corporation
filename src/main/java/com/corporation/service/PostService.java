package com.corporation.service;

import com.corporation.dto.PostDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.model.PostStatistics;
import com.corporation.model.Project;
import com.corporation.model.User;
import com.corporation.repository.PostRepository;
import com.corporation.repository.PostStatisticsRepository;
import com.corporation.service.event.LikeEventPublisher;
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
    private final UserService userService;
    private final ProjectService projectService;
    private final LikeEventPublisher likeEventPublisher;
    private final PostStatisticsRepository postStatisticsRepository;

    public PostDto savePostDraft(PostDto postDto) {
        User user = userService.findById(postDto.getUserId());
        Long projectId = postDto.getProjectId();
        Project project = projectId == null ? null : projectService.findById(projectId);

        Post post = postMapper.toEntity(postDto);
        post.setUser(user);
        post.setProject(project);
        post.setPublished(false);
        post = postRepository.save(post);

        PostStatistics postStatistics = new PostStatistics();
        postStatistics.setPostId(post.getId());
        postStatisticsRepository.save(postStatistics);
        post.setPostStatistics(postStatistics);

        return postMapper.toDto(post);
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

    @Transactional
    public void addLike(long postId, long userId) {
        Post post = findById(postId);
        User user = userService.findById(userId);

        likeEventPublisher.addLike(post, user);
    }
}
