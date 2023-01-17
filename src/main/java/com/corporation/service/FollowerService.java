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
    public void followProject(long followingProjectId, long projectFollowerId) {
        followerRepository.followProject(followingProjectId, projectFollowerId);
    }
}
