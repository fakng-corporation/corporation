package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.mapper.UserMapper;
import com.corporation.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final UserMapper userMapper;

    @Transactional
    public void followProject(long projectId, long followerId) {
        followerRepository.followProject(projectId, followerId);
    }

    @Transactional
    public void followUser(long followerId, long followeeId) {
        followerRepository.followUser(followerId, followeeId);
    }

    @Transactional
    public void unfollowUser(long followerId, long followeeId) {
        followerRepository.unfollowUser(followerId, followeeId);
    }

    @Transactional
    public void unfollowProject(long projectId, long followerId) {
        followerRepository.unfollowProject(projectId, followerId);
    }

    @Transactional
    public Page<UserDto> findProjectSubscribers(long projectId, String keyword, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return followerRepository.findProjectSubscribers(projectId, keyword, page).map(userMapper::toDto);
    }
}