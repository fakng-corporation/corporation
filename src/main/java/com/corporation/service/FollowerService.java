package com.corporation.service;

import com.corporation.model.User;
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

    @Transactional
    public void followProject(long projectId, long followerId) {
        followerRepository.followProject(projectId, followerId);
    }

    @Transactional
    public Page<User> findProjectSubscribers(long projectId, String keyword, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return followerRepository.findByFollowingProjectsIdAndNicknameContainingIgnoreCaseOrderById(projectId, keyword, page);
    }
}