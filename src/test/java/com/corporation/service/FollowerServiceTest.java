package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.repository.FollowerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FollowerServiceTest {
    @Mock
    private FollowerRepository followerRepository;
    @InjectMocks
    private FollowerService followerService;

    @Spy
    @InjectMocks
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldFollowProject() {
        long followerId = 2L;
        long projectId = 3L;
        Mockito.doNothing().when(followerRepository).followProject(projectId, followerId);

        followerService.followProject(projectId, followerId);
        Mockito.verify(followerRepository).followProject(projectId, followerId);
    }

    @Test
    public void shouldFollow() {
        long followerId = 1L;
        long followeeId = 2L;

        followerService.followUser(followerId, followeeId);
        Mockito.verify(followerRepository).followUser(followerId, followeeId);
    }

    @Test
    public void shouldUnfollow() {
        long followerId = 1L;
        long followeeId = 2L;

        followerService.unfollowUser(followerId, followeeId);
        Mockito.verify(followerRepository).unfollowUser(followerId, followeeId);
    }

    @Test
    void shouldUnfollowProject() {
        long followerId = 2L;
        long projectId = 3L;
        Mockito.doNothing().when(followerRepository).unfollowProject(projectId, followerId);

        followerService.unfollowProject(projectId, followerId);
        Mockito.verify(followerRepository).unfollowProject(projectId, followerId);
    }

    @Test
    public void shouldReturnUsersByProjectIdAndNickname() {
        long projectId = 4L;
        long projectFollowerId = 1l;
        String keyword = "User";
        User user = User.builder().id(projectFollowerId).nickname("User1").build();
        int pageNumber = 2;
        int pageSize = 3;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        UserDto userdto = userMapper.toDto(user);

        Mockito.when(followerRepository.findByFollowingProjectsIdAndNicknameContainingIgnoreCaseOrderById(projectId, keyword, page))
                .thenReturn(new PageImpl<>(List.of(user)));
        Page<UserDto> actualResult = followerService.findProjectSubscribers(projectId, keyword, pageNumber, pageSize);
        Assertions.assertEquals(new PageImpl<>(List.of(userdto)), actualResult);
    }
}
