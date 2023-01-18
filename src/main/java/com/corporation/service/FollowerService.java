package com.corporation.service;

import com.corporation.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;

    @Transactional
    public void followProject(long projectId, long followerId) {
        followerRepository.followProject(projectId, followerId);
    }

    @Transactional
    public void followUser(long followerId, long followeeId) {
        followerRepository.followUser(followerId, followeeId);
    }
}
