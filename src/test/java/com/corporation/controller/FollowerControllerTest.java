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
    void shouldUnfollowProject() {
        long projectId = 4L;
        User unfollower = User.builder().id(3L).nickname("User2").email("user2@domain.com").password("admin").enabled(true).build();

        followerController.unfollowProject(projectId, unfollower);
        Mockito.verify(followerService).unfollowProject(projectId, unfollower.getId());
    }
}
