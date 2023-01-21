package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.model.User;
import com.corporation.service.FollowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

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

        followerService.followUser(followerId, followeeId);
        Mockito.verify(followerService).followUser(followerId, followeeId);
    }

    @Test
    public void shouldUnfollow() {
        long followerId = 1L;
        long followeeId = 2L;

        followerService.unfollowUser(followerId, followeeId);
        Mockito.verify(followerService).unfollowUser(followerId, followeeId);
    }

    @Test
    void shouldUnfollowProject() {
        long projectId = 4L;
        User follower = User.builder().id(3L).nickname("User2").email("user2@domain.com").password("admin").enabled(true).build();

        followerController.unfollowProject(projectId, follower);
        Mockito.verify(followerService).unfollowProject(projectId, follower.getId());
    }

    @Test
    public void shouldReturnUsersByProjectIdAndNickname() {
        long projectId = 4L;
        long projectFollowerId = 1l;
        String keyword = "User";
        int pageNumber = 2;
        int pageSize = 3;
        UserDto userdto = UserDto.builder().id(projectFollowerId).nickname("User1").build();
        Page<UserDto> expectedResult = new PageImpl<>(List.of(userdto));

        Mockito.when(followerService.findProjectSubscribers(projectId, keyword, pageNumber, pageSize)).thenReturn(expectedResult);
        Page<UserDto> actualResult = followerController.getProjectSubscribers(projectId, keyword, pageNumber, pageSize);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
