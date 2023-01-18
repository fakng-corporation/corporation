package com.corporation.service;

import com.corporation.repository.FollowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FollowerServiceTest {
    @Mock
    private FollowerRepository followerRepository;
    @InjectMocks
    private FollowerService followerService;

    @Test
    void shouldFollowProject() {
        long followerId = 2L;
        long projectId = 3L;
        Mockito.doNothing().when(followerRepository).followProject(projectId, followerId);

        followerService.followProject(projectId, followerId);
        Mockito.verify(followerRepository).followProject(projectId, followerId);
    }

    @Test
    void shouldUnfollowProject() {
        long followerId = 2L;
        long projectId = 3L;
        Mockito.doNothing().when(followerRepository).unfollowProject(projectId, followerId);

        followerService.unfollowProject(projectId, followerId);
        Mockito.verify(followerRepository).unfollowProject(projectId, followerId);
    }
}
