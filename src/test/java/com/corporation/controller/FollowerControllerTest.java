package com.corporation.controller;

import com.corporation.model.User;
import com.corporation.service.FollowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FollowerControllerTest {
    @Mock
    private FollowerService followerService;

    @InjectMocks
    private FollowerController followerController;

    @Test
    void shouldFollowProject() {
        long projectId = 2L;
        User follower = User.builder().id(3L).nickname("User2").email("user2@domain.com").password("admin").enabled(true).build();
        Mockito.doNothing().when(followerService).followProject(projectId, follower.getId());

        followerController.followProject(projectId, follower);
        Mockito.verify(followerService).followProject(projectId, follower.getId());
        Assertions.assertDoesNotThrow(() -> followerController.followProject(projectId, follower));
    }

    @Test
    public void shouldFollow() {
        long followerId = 1L;
        long followeeId = 2L;

        followerService.unfollowUser(followerId, followeeId);
        Mockito.verify(followerService).unfollowUser(followerId, followeeId);
    }
}
